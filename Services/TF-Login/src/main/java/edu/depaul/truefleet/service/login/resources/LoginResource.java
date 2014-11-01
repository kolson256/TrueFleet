package edu.depaul.truefleet.service.login.resources;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import edu.depaul.truefleet.service.login.core.User;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;



@Path("/login-check")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    public LoginResource() {
    }

    @GET
    @Timed
    public String isLoggedIn()
    {
        final Subject s = SecurityUtils.getSubject();
        if (s != null && s.isAuthenticated()) {
            return String.format("Logged in as '%s'.", s.getPrincipal());
        } else {
            return "Not logged in.";
        }
    }
}
