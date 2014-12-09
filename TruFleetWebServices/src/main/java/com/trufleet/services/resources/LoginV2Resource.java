package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Richard Morgan on 12/8/2014.
 */


@Path("/LoginV2")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginV2Resource extends BaseResource {

    public LoginV2Resource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);

    }



}
