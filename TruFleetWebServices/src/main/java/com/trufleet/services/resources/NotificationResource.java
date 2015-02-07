package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.representations.AppUser;
import com.trufleet.services.jdbi.AppUserDAO;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


/**
 * Created by Chris Lacy on 11/15/2014.
 */
@Path("/Notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationResource extends BaseResource {
    //api key of server..should be final when server is on linux box
    final String API_KEY = "AIzaSyCAwtVOmKEKsy-s6DDlgkG9thLqfO9aNMQ";
    final String GCM_URL = "https://android.googleapis.com/gcm/send";

    public NotificationResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    /*
        JSON Body requires an username of the person to be notified.
        and the internalOrderID of the order that user was assigned.
     */
    @POST
    public Response notification( @HeaderParam("authToken") String authToken,
                                  @HeaderParam("tenantId") String tenantId,
                                  String body) throws ClassNotFoundException, JSONException, MalformedURLException {

        JSONObject response = new JSONObject();
        JSONObject returnObj = new JSONObject();

        //UserName of person to be notified.  not of the user initiating the notification.
        String username;

        //UserLoginDAO userLoginDAO = getAdminDb().onDemand(UserLoginDAO.class);
        String internalId;

        try {
            JSONObject request = new JSONObject(body);
            username = request.getString("username");
            internalId = request.getString("internalId");

        } catch (JSONException e) {
            e.printStackTrace();
            response.put("errorMessage", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("JSON serialization error: " + Arrays.toString(e.getStackTrace()))
                    .build();
        }
        //  Query for the username, and check that the credentials are valid
        /*UserLogin userLogin = userLoginDAO.findUserLoginbyUserName(username);
        if (userLogin == null) {
            response.put("errorMessage", "Username Not Found");
            return response.toString();
        }*/

        try {
            buildTenantDb(tenantId);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Build tenant DB failed: " + Arrays.toString(e.getStackTrace()))
                    .build();
        }

        AppUserDAO appUserDAO = getTenantDb().onDemand(AppUserDAO.class);

        AppUser appUser = appUserDAO.findAppUserbyUserName(username);

        try
        {
            URL url = new URL(GCM_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key="+API_KEY);
            conn.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(conn.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();

            Content content = new Content();
            content.addRegId(appUser.getRegistrationId());
            content.addData("title", "orderUpdate");
            content.addData("user","username");
            content.addData("internalId", internalId);

        mapper.writeValue(dataOutputStream, content);

        dataOutputStream.flush();
        dataOutputStream.close();

            //Getting and displaying response for testing..
            int responseCode = conn.getResponseCode();
            if (responseCode!= 200) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("GCM failed getting to Google.")
                        .build();
            }
            else
                return Response.ok().build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.serverError()
                .entity("Undetermined Error").build();
    }

}

class Content implements Serializable {

    public List<String> registration_ids;
    public Map<String, String> data;

    public void addRegId(String regId) {
        if (registration_ids == null)
            registration_ids = new LinkedList<String>();
        registration_ids.add(regId);
    }

    public void addData(String title, String message) {

        if (data == null)
            data = new HashMap<String, String>();
        data.put(title, message);
    }
}

