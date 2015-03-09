package com.trufleet.services.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.LinehaulEntityDAO;
import com.trufleet.services.domain.representations.LinehaulEntity;
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
@Path("/0.1/linehaul")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class LinehaulResource {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(LinehaulResource.class);

    private final LinehaulEntityDAO dao;

    public LinehaulResource(LinehaulEntityDAO linehaulEntityDAO) {


        dao = linehaulEntityDAO;
    }



    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public LinehaulEntity queryLinehaulByID (@PathParam("id") int id) {
        return dao.findById(id);
    }


    @POST
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public int createLinehaul(@Valid LinehaulEntity linehaul) throws JSONException, IOException {

        return dao.create(linehaul);

    }

    @PUT
    @Timed
    @UnitOfWork
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public LinehaulEntity updateLinehaul(@PathParam("id") int id, @Valid LinehaulEntity linehaul)
            throws JSONException, IOException {

        return dao.modifyContact(linehaul);
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
    public void removeContact(@PathParam("id") int id, @Valid LinehaulEntity linehaul)
            throws JSONException, IOException{

        dao.removeContact(linehaul);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/order/{orderid}")
    public List<LinehaulEntity> listLinehaulsByOrderId (@PathParam("orderid") int id){
        return dao.queryLinehaulsByOrderID(id);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/account/{accountid}")
    public List<LinehaulEntity> listLinehaulsByAccountId (@PathParam("accountid") int id){
        return dao.queryLinehaulsByAccountID(id);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/status/{statusid}")
    public List<LinehaulEntity> listLinehaulsByStatus (@PathParam("statusid") int id){
        return dao.queryLinehaulsByStatus(id);
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/route/{routeid}")
    public List<LinehaulEntity> listLinehaulsByRouteId (@PathParam("routeid") int id){
        return dao.queryLinehaulsByRouteID(id);
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/orderroute/")
    public List<LinehaulEntity> listLinehaulsByRouteId (int routeid, int orderid){
        return dao.queryLinehaulByRouteAndOrder(routeid, orderid);
    }



}

