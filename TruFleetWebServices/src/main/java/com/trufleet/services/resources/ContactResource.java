package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.ContactEntityDAO;
import com.trufleet.services.domain.representations.ContactEntity;
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
 * Created by Richard Morgan on 2/9/2015.
 */

@Path("/0.1/contact")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ContactResource {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactResource.class);

    private ContactEntityDAO dao;

    public ContactResource(ContactEntityDAO contactEntityDAO){
        dao = contactEntityDAO;
    }


    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public ContactEntity queryContactByID (@PathParam("id") int id) {
        return dao.findById(id);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/account/{id}")
    public List<ContactEntity> queryContactByAccountID(@PathParam("id") int id){
        return dao.getByAccountID(id);
    }

    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createContact(@Valid ContactEntity contact) throws JSONException, IOException {

        return dao.create(contact);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ContactEntity updateContact(@PathParam("id") int id, @Valid ContactEntity contact)
            throws JSONException, IOException {

        return dao.modifyContact(contact);
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
    public void removeContact(@PathParam("id") int id, @Valid ContactEntity contact)
            throws JSONException, IOException{

        dao.removeContact(contact);
    }



}
