package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.ContainerEntityDAO;
import com.trufleet.services.domain.representations.ContainerEntity;
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
@Path("/0.1/contact")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ContainerResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ContainerResource.class);

    private final ContainerEntityDAO dao;

    public ContainerResource(ContainerEntityDAO ContainerEntityDAO) {


        dao = ContainerEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public ContainerEntity queryContainerByID (@PathParam("id") int id) {
        return dao.findById(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createContainer(@Valid ContainerEntity container) throws JSONException, IOException {

        return dao.create(container);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ContainerEntity updateContainer(@PathParam("id") int id, @Valid ContainerEntity container )
            throws JSONException, IOException {

        return dao.modifyContact(container);
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
    public void removeContact(@PathParam("id") int id, @Valid ContainerEntity container)
            throws JSONException, IOException{

        dao.removeContact(container);
    }




}

