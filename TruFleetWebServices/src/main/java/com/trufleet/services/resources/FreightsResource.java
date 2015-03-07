package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.FreightEntityDAO;
import com.trufleet.services.domain.representations.ContactEntity;
import com.trufleet.services.domain.representations.FreightEntity;
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
@Path("/0.1/freights")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class FreightsResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(FreightResource.class);

    private final FreightEntityDAO dao;

    public FreightsResource(FreightEntityDAO freightEntityDAO) {


        dao = freightEntityDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{linehaulid}") //returning a list of the same stub freight from DB instead of real...
    public List<ContactEntity> getContacts (@PathParam("accountid") int id) {
        FreightEntity f1 = dao.findById(2); //2 does not correspond to account id, this is contact id.
        List l = new ArrayList();
        l.add(f1);
        l.add(f1);

        return l;
    }
}
