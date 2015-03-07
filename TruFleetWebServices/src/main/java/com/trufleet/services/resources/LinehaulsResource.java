package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.LinehaulEntityDAO;
import com.trufleet.services.domain.representations.ContactEntity;
import com.trufleet.services.domain.representations.LinehaulEntity;
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
@Path("/0.1/linehauls")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LinehaulsResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(LinehaulResource.class);

    private final LinehaulEntityDAO dao;

    public LinehaulsResource(LinehaulEntityDAO linehaulEntityDAO) {


        dao = linehaulEntityDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{routeid}/{orderid}") //returning a list of the same stub linehauls from DB instead of real...
    public List<LinehaulEntity> getLinehauls (@PathParam("accountid") int id) {
        LinehaulEntity l1 = dao.findById(1);
        List l = new ArrayList();
        l.add(l1);
        l.add(l1);

        return l;
    }
}
