package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.FreightEntityDAO;
import com.trufleet.services.domain.representations.FreightEntity;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jackson.Jackson;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
@Path("/0.1/freight")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class FreightResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(FreightResource.class);

    private final FreightEntityDAO dao;

    public FreightResource(FreightEntityDAO freightEntityDAO) {
        dao = freightEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public FreightEntity queryFreightByID (@PathParam("id") int id) {
        return dao.findById(id);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/container/{id}")
    public List<FreightEntity> queryFrieghtByContainerID(@PathParam("id") int id){
       return dao.findByContainer(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createFreight(@Valid FreightEntity freight) throws JSONException, IOException {

        return dao.create(freight);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public FreightEntity updateFreight(@PathParam("id") int id, @Valid FreightEntity freight)
            throws JSONException, IOException {

        return dao.modifyContact(freight);
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
    public void removeContact(@PathParam("id") int id, @Valid FreightEntity freight)
            throws JSONException, IOException{

        dao.removeContact(freight);
    }




}
