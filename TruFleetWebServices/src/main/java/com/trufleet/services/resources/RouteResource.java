package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.RouteEntityDAO;
import com.trufleet.services.domain.representations.RouteEntity;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jackson.Jackson;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
@Path("/0.1/route")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class RouteResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteResource.class);

    private final RouteEntityDAO dao;

    public RouteResource(RouteEntityDAO routeEntityDAO) {


        dao = routeEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public RouteEntity queryRouteByID (@PathParam("id") int id) {
        return dao.findById(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createRoute(@Valid RouteEntity route) throws JSONException, IOException {

        return dao.create(route);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public RouteEntity updateRoute(@PathParam("id") int id, @Valid RouteEntity route)
            throws JSONException, IOException {

        return dao.modifyContact(route);
    }

    /*
       Delete an Organization.

       Should this have a return?
    */
    @DELETE
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeContact(@PathParam("id") int id, @Valid RouteEntity route)
            throws JSONException, IOException{

        dao.removeContact(route);
    }




}

