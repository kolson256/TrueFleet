package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.IntermodalContainer;
import com.trufleet.services.jdbi.IntermodalContainerDAO;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Richard Morgan on 11/19/2014.
 */

@Path("/0.1/container")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class IntermodalContainerResource extends BaseResource{
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private final IntermodalContainerDAO imtdao = getTenantDb().onDemand(IntermodalContainerDAO.class);

    private String version = getVersion();

    public IntermodalContainerResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @GET
    public List<IntermodalContainer> getAllContainers(){
        return imtdao.findAllContainers();
    }

    //Useless for now, as if you have the id, you have the whole object.
    @GET
    @Path("/{id}")
    public IntermodalContainer queryContainerById(@PathParam("id") String id) {
        return imtdao.findContainerById(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public IntermodalContainer createContainer(@Valid IntermodalContainer cont) throws JSONException, IOException{
        IntermodalContainer container = cont;

        if( null == imtdao.findContainerById(container.getId())){
            imtdao.insertContainer(container);
        }
        //TODO add else.
        //TODO: change to jax-rs response type with location to new resource.
        return imtdao.findContainerById(container.getId());

    }

    /*
        Not needed yet, Container is just ID for now.
    */
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateOrganization(@PathParam("name") String name, @Valid IntermodalContainer imc)
//            throws JSONException, IOException{
//
//        //verify org named exists, get that object.
//        IntermodalContainer checkImt = imtdao.findOrganizationById(imc.getId());
//
//        if( null != checkImt ) {
//
//        }
//        //TODO: compare existing to request to verify that there is a change?
//
//        //TODO:  Create a return message?
//
//    }
        /*
       Delete an Organization.

       Should this have a return?
    */
    @DELETE @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeContainer(@PathParam("id") String id, @Valid IntermodalContainer imc)
            throws JSONException, IOException{

        //verify that org exists
        IntermodalContainer checkImt = imtdao.findContainerById(imc.getId());

        if( null != checkImt ) {
            IntermodalContainer container = imc;
            imtdao.removeContainer(container);
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();

    }


}
