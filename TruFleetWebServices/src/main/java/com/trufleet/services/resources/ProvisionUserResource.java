package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.dao.AppUserDAO;
import com.trufleet.services.dao.UserLoginDAO;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Kyle Olson on 11/6/2014.
 */
@Path("/0.1/ProvisionUser")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProvisionUserResource extends BaseResource {

    public ProvisionUserResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @POST
    public String provisionUser(String body, @HeaderParam("authToken") String authToken,
                                @HeaderParam("tenantId") String tenantId) throws JSONException {

        JSONObject response = new JSONObject();
        try {
            buildTenantDb(tenantId, authToken);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());
            return response.toString();
        }

        UserLoginDAO userLoginDAO = getAdminDb().onDemand(UserLoginDAO.class);
        AppUserDAO appUserDAO = getTenantDb().onDemand(AppUserDAO.class);

        JSONObject request = new JSONObject(body);
        String username = request.getString("username");
        String password = request.getString("password");
        String firstName = request.getString("firstName");
        String lastName = request.getString("lastName");

        userLoginDAO.begin();
        appUserDAO.begin();
        try {
            userLoginDAO.insert(username, password, getOrganization().getTenantID());
            appUserDAO.insert(username, firstName, lastName);
        }
        catch (UnableToExecuteStatementException e) {
            userLoginDAO.rollback();
            appUserDAO.rollback();
            response.put("errorMessage", e.getMessage());
            return response.toString();
        }

        appUserDAO.commit();
        userLoginDAO.commit();

        response.put("message", "success");
        return response.toString();
    }
}
