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
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Arrays;
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

    /*
        JSON Body requires an username of the app user registering GCM
        and the GCM registration ID
     */

    @POST
    public Response gcmRegistration(String body, @HeaderParam("authToken") String authToken, @HeaderParam("tenantId") String tenantId) throws ClassNotFoundException, JSONException {
        JSONObject response = new JSONObject();
        UserLoginDAO userLoginDAO = getAdminDb().onDemand(UserLoginDAO.class);


        String username;
        String registrationId;
        Timestamp expirationDate;

        try {
            JSONObject request = new JSONObject(body);
            System.out.println(request.toString());
            username = request.getString("username");
            registrationId = request.getString("registrationId");
            buildTenantDb(tenantId, authToken);

        } catch (JSONException e) {
            e.printStackTrace();
            response.put("errorMessage", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("JSON serialization error: " + Arrays.toString(e.getStackTrace()))
                    .build();
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unknown Server error")
                    .build();
        }

        AuthTokenDAO authTokenDAO = getTenantDb().onDemand(AuthTokenDAO.class);
        AuthToken authTokenObj = authTokenDAO.findAuthToken(authToken);
        AppUserDAO appUserDAO = getTenantDb().onDemand(AppUserDAO.class);

        if (authTokenObj == null) {
            response.put("errorMessage", "AuthToken Not Found");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Authentication token not found")
                    .build();

        } else {
            expirationDate = authTokenObj.getExpirationDate();
            if (expirationDate.before(new Date())) {
                response.put("errorMessage", "AuthToken expired");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Authentication token expired")
                        .build();
            }

            appUserDAO.updateRegistrationId(username, registrationId);
            response.put("message", "success");
            return Response.status(Response.Status.OK).build();
        }
    }

}
