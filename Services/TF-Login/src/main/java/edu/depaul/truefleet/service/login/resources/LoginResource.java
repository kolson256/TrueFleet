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
    @Timed
    public String login(@FormParam("username") String name, @FormParam("password") String password ) {

        Optional<UserLogin> userLogin = Optional.fromNullable( dao.FindUserLoginbyUserName( name ) );

        if (userLogin.isPresent()){
            if( userLogin.get().getPassword().equals(password)){
                return "status: login successful";
            }else{
                return "status: login failed.";
            }
        }else{
            return "status: user does not exist.";
        }


    }

}
