package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.ContactEntityDAO;
import com.trufleet.services.domain.representations.ContactEntity;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jackson.Jackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Lacy on 3/7/2015.
 */
@Path("/0.1/contacts")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ContactsResource {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactResource.class);

    private ContactEntityDAO dao;

    public ContactsResource(ContactEntityDAO contactEntityDAO){
        dao = contactEntityDAO;
    }


    @GET
    @Timed
    @UnitOfWork
    @Path("/{accountid}") //returning a list of the same stub contact from DB instead of real...
    public List<ContactEntity> getContacts (@PathParam("accountid") int id) {
        ContactEntity c1 = dao.findById(2); //2 does not correspond to account id, this is contact id.
        List l = new ArrayList();
        l.add(c1);
        l.add(c1);

        return l;
    }
}
