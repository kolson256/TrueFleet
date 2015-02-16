package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.ChargeEntityDAO;
import com.trufleet.services.domain.representations.ChargeEntity;
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


@Path("/0.1/charge")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ChargeResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeResource.class);

    private final ChargeEntityDAO dao;

    public ChargeResource(ChargeEntityDAO chargeEntityDAO) {


        dao = chargeEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public ChargeEntity queryChargeByID (@PathParam("id") int id) {
        return dao.findById(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createCharge(@Valid ChargeEntity charge) throws JSONException, IOException {

        return dao.create(charge);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ChargeEntity updateCharge(@PathParam("id") int id, @Valid ChargeEntity charge)
            throws JSONException, IOException {

        return dao.modifyContact(charge);
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
    public void removeContact(@PathParam("id") int id, @Valid ChargeEntity charge)
            throws JSONException, IOException{

        dao.removeContact(charge);
    }




}
