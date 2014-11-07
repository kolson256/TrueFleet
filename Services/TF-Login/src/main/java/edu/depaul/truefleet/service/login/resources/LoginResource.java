package edu.depaul.truefleet.service.login.resources;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import edu.depaul.truefleet.service.login.core.AppUser;
import edu.depaul.truefleet.service.login.core.Organization;
import edu.depaul.truefleet.service.login.core.UserLogin;
import edu.depaul.truefleet.service.login.dao.UserLoginDAO;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import org.json.JSONException;
import org.json.JSONObject;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Calendar;
import java.util.UUID;
import java.util.Date;
import java.sql.Timestamp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final UserLoginDAO dao;
    private final Environment environment;

    public LoginResource(UserLoginDAO dao, Environment environment) {
        this.dao = dao;
        this.environment = environment;
    }

    @POST
        public String login(String body) throws ClassNotFoundException {

            try {
                JSONObject json = new JSONObject(body);
                String username = json.getString("username");
                String password = json.getString("password");

                UserLogin userLogin = dao.findUserLoginbyUserName(username);

                if (userLogin == null)
                    return "Username not found";

                Organization organization = dao.findOrganizationbyTenantId(userLogin.getOrganizationId());
                System.out.println( "getDatabaseURL: " + organization.getDatabaseURL());

                DataSourceFactory dataSourceFactory = new DataSourceFactory();
                dataSourceFactory.setUrl(organization.getDatabaseURL());
                dataSourceFactory.setUser("postgres");
                dataSourceFactory.setPassword("password");
                dataSourceFactory.setDriverClass("org.postgresql.Driver");

                final DBIFactory factory = new DBIFactory();
                final DBI jdbi = factory.build(environment, dataSourceFactory, "TruFleetTenant");
                UserLoginDAO daoTenant = jdbi.onDemand(UserLoginDAO.class);

                AppUser appUser = daoTenant.findAppUserbyUserName( username );

                UUID uuid = UUID.randomUUID();
                daoTenant.insertToken(appUser.getId(), uuid.toString(), new Timestamp(Calendar.getInstance().getTime().getTime()));

                JSONObject response = new JSONObject();
                response.put("authenticationToken", uuid.toString());
                response.put("tenantId", organization.getTenantID());
                response.put("apiVersion", organization.getApiVersion());

                if( userLogin.getPassword().equals(password)){
                    return response.toString();
                }else{
                    return "Incorrect Password";
                }

            } catch (JSONException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }

}
