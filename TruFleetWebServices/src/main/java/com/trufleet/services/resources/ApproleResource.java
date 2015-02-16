package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.ApproleEntityDAO;
import com.trufleet.services.domain.representations.ApproleEntity;
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

@Path("/0.1/approle")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ApproleResource {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ApproleResource.class);

    private final ApproleEntityDAO dao;

    public ApproleResource(ApproleEntityDAO approleEntityDAO) {

        dao = approleEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public ApproleEntity queryApproleByID (@PathParam("id") int id) {
        return dao.findById(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createApprole(@Valid ApproleEntity approle) throws JSONException, IOException {
        return dao.create(approle);
    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ApproleEntity updateApprole(@PathParam("id") int id, @Valid ApproleEntity approle)
            throws JSONException, IOException {
        return dao.modifyContact(approle);
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
    public void removeContact(@PathParam("id") int id, @Valid ApproleEntity approle)
            throws JSONException, IOException{
        dao.removeContact(approle);
    }



}
