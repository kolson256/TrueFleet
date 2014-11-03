package edu.depaul.truefleet.service.login.resources;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import edu.depaul.truefleet.service.login.core.UserLogin;
import edu.depaul.truefleet.service.login.dao.UserLoginDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final UserLoginDAO dao;

    public LoginResource(UserLoginDAO dao) {
        this.dao = dao;
    }

    @POST
        public String login(String body){

            System.out.println("body: " + body);
            try {
                JSONObject json = new JSONObject(body);
                String username = json.getString("username");
                String password = json.getString("password");

                System.out.println( "username: " + username );
                System.out.println( "password: " + password );

                UserLogin userLogin = dao.FindUserLoginbyUserName( username );


                System.out.println( "userLogin: " + userLogin);
                System.out.println( "getUsername: " + userLogin.getUsername());
                System.out.println( "getPassword: " + userLogin.getPassword());
                System.out.println( "getOrganizationId: " + userLogin.getOrganizationId());

                if( userLogin.getPassword().equals(password)){
                    return "login successful";
                }else{
                    return "login failed.";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "login failed.";
        }

}
