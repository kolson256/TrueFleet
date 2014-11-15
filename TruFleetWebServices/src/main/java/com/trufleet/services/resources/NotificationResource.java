package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import io.dropwizard.setup.Environment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skife.jdbi.v2.DBI;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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

    @POST
    public String notification(String body) throws ClassNotFoundException, JSONException, MalformedURLException {
        JSONObject returnObj = new JSONObject();
        System.out.println("in notification");

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

            //this would be unique to a user that could change for the user
            content.addRegId("APA91bH9D4j86cYTBs4O_7m3yVai-FwLEEMKUKznByArDEHju-RBUgRAue_7YcGI190azqBZMopIG4xhTD6a1EQyURlUd3q_seAwVJTYgx3yx3YylmAeTVChjicAJxzAb00-Hu8R2nsy6dbomb29rlSxojrfzHQgz4SFGl-y2SDlbqJimA9TK4k");
            content.addData("title", "orderUpdate");
            content.addData("user","username");

        mapper.writeValue(dataOutputStream, content);

        dataOutputStream.flush();
        dataOutputStream.close();

            //Getting and displaying response for testing..
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            System.out.println(conn.getResponseMessage());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer resp = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            System.out.println(resp.toString());
            in.close();

            return returnObj.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnObj.toString();
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

