package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.AppUser;
import com.trufleet.services.core.AuthToken;
import com.trufleet.services.core.UserLogin;
import com.trufleet.services.jdbi.*;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
@Path("/Login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource extends BaseResource {

    public LoginResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @POST
    public String login(String body) throws ClassNotFoundException, JSONException {
        JSONObject response = new JSONObject();
        UserLoginDAO userLoginDAO = getAdminDb().onDemand(UserLoginDAO.class);

        //  Extract the user name and password from the request
        String username;
        String password;
        try {
            JSONObject request = new JSONObject(body);
            username = request.getString("username");
            password = request.getString("password");

        } catch (JSONException e) {
            e.printStackTrace();
            response.put("errorMessage", e.getMessage());
            return response.toString();
        }

        //  Query for the username, and check that the credentials are valid
        UserLogin userLogin = userLoginDAO.findUserLoginbyUserName(username);

        if (userLogin == null) {
            response.put("errorMessage", "Username Not Found");
            return response.toString();
        }

        if (!userLogin.getPassword().equals(password)) {
            response.put("errorMessage", "Invalid password");
            return response.toString();
        }

        //  Find the user and current token in the tenant database
        AppUserDAO appUserDAO = getTenantDb().onDemand(AppUserDAO.class);
        AuthTokenDAO authTokenDAO = getTenantDb().onDemand(AuthTokenDAO.class);
        AppUser appUser = appUserDAO.findAppUserbyUserName(username);
        AuthToken authToken = authTokenDAO.findAuthTokenByUserId(appUser.getId());

        if (authToken == null || authToken.getExpirationDate().before(new Date())) {
            //  Create the authentication token and set its expiration date to 4 hours from now
            UUID uuid = UUID.randomUUID();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 4);
            authTokenDAO.insertToken(appUser.getId(), uuid.toString(), new Timestamp(cal.getTime().getTime()));
            response.put("authenticationToken", uuid.toString());
        }
        else {
            response.put("authenticationToken", authToken.getToken());
        }

        response.put("tenantId", userLogin.getTenantId());
        response.put("apiVersion", getOrganization().getApiVersion());
        return response.toString();
    }
}
