package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
//import com.trufleet.services.jdbi.DriverUserDAO;
import com.trufleet.services.core.AppUser;
import com.trufleet.services.core.AuthToken;
import com.trufleet.services.core.UserLogin;
import com.trufleet.services.jdbi.AppUserDAO;
import com.trufleet.services.jdbi.AuthTokenDAO;
import com.trufleet.services.jdbi.UserLoginDAO;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by night_000 on 11/17/2014.
 */
@Path("/GcmRegistration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GcmRegistrationResource extends BaseResource {
    public GcmRegistrationResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }
    @POST
    public String gcmRegistration(String body, @HeaderParam("authToken") String authToken) throws ClassNotFoundException, JSONException {
        JSONObject response = new JSONObject();
        UserLoginDAO userLoginDAO = getAdminDb().onDemand(UserLoginDAO.class);

        String username;
        long userId;
        String tenantId;
        String registrationId;
        Timestamp expirationDate;

        try {
            JSONObject request = new JSONObject(body);
            username = request.getString("username");
            registrationId = request.getString("registrationId");

        } catch (JSONException e) {
            e.printStackTrace();
            response.put("errorMessage", e.getMessage());
            return response.toString();
        }

        UserLogin userLogin = userLoginDAO.findUserLoginbyUserName(username);

        if (userLogin == null) {
            response.put("errorMessage", "Username Not Found");
            return response.toString();
        }
        tenantId = userLogin.getTenantId();
        try {
            buildTenantDb(tenantId, authToken);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            return response.toString();
        }

        AuthTokenDAO authTokenDAO = getTenantDb().onDemand(AuthTokenDAO.class);
        AuthToken authTokenObj = authTokenDAO.findAuthToken(authToken);

        if (authTokenObj == null) {
            response.put("errorMessage", "AuthToken Not Found");
            return response.toString();

        } else {
            userId = authTokenObj.getAppUserId();
            expirationDate = authTokenObj.getExpirationDate();
            if (expirationDate.before(new Date())) {
                response.put("errorMessage", "AuthToken expired");
            }

            //TODO: Add to database when exists
            response.put("message", "success");
        }
        return response.toString();
    }

}
