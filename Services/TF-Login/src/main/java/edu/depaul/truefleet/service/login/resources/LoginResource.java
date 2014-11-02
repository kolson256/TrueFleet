package edu.depaul.truefleet.service.login.resources;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

import com.codahale.metrics.annotation.Timed;
import edu.depaul.truefleet.service.login.core.UserLogin;
import edu.depaul.truefleet.service.login.dao.UserLoginDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final UserLoginDAO dao;

    public LoginResource(UserLoginDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    public String login(@QueryParam("username") String name, @QueryParam("password") String password ) {

        UserLogin userLogin = dao.FindUserLoginbyUserName( name );

        if( userLogin.getPassword().equals(password)){
            return "login successful";
        }else{
            return "login failed.";
        }

    }

    @POST
    public void register(@QueryParam("username") String name, @QueryParam("password") String password, @QueryParam("orgID") long orgid){
        dao.insert( name, password, orgid);
    }


}
