package com.trufleet.services.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.auth.InvalidTenantIdException;
import com.trufleet.services.auth.InvalidTokenException;
import com.trufleet.services.core.IntermodalContainer;
import com.trufleet.services.jdbi.IntermodalContainerDAO;
import com.trufleet.services.message.StatusJson;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Richard Morgan on 11/19/2014.
 */

@Path("/0.1/container")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class IntermodalContainerResource extends BaseResource{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(IMTOrderResource.class);
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private IntermodalContainerDAO imtdao;

    //private String version = getVersion();

    public IntermodalContainerResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @GET
    public Response getAllContainers(@HeaderParam("authToken") String authToken,
                                                      @HeaderParam("tenantId") String tenantId){

        try {
            imtdao = getDAO(authToken, tenantId);
        }catch (Exception e){
            logger.error("Get tenant DB failed.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errormessage",
                            "Get tenant DB failed. " + Arrays.toString(e.getStackTrace())))
                    .build();
        }

        List<IntermodalContainer> containers = imtdao.findAllContainers();

/*        String responseJson;
        try {
            responseJson = MAPPER.writeValueAsString(containers);

        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            logger.error("JSON Serialization of containers failed: " + Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }*/

        //TODO: instead of full JSON representation - send only URIs of Containers.  Perhaps only "1 page" of containers.
        return Response.ok(containers).build();
    }

    //Useless for now, as if you have the id, you have the whole object.
    @GET
    @Path("/{id}")
    public Response queryContainerById(@HeaderParam("authToken") String authToken,
                                                  @HeaderParam("tenantId") String tenantId,
                                                  @PathParam("id") String id) {

        try {
            imtdao = getDAO(authToken, tenantId);
        }catch (Exception e){
            logger.error("Get tenant DB failed.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errormessage",
                            "Get tenant DB failed. " + Arrays.toString(e.getStackTrace())))
                    .build();
        }

        return Response.ok(imtdao.findContainerById(id)).build();

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContainer(@HeaderParam("authToken") String authToken,
                                    @HeaderParam("tenantId") String tenantId,
                                    @Valid IntermodalContainer cont) throws JSONException, IOException{

        logger.info(">>>>>> The passed Auth token is: " + authToken
                        + "\nThe passed Tenant ID is: " + tenantId);

        try {
            imtdao = getDAO(authToken, tenantId);
        }catch (Exception e){
            logger.error("Get tenant DB failed.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errormessage",
                            "Get tenant DB failed. " + Arrays.toString(e.getStackTrace())))
                    .build();
        }

        if( null == imtdao.findContainerById(cont.getId())){
            imtdao.insertContainer(cont);
        }else{
            return Response.status(Response.Status.CONFLICT)
                    .entity("Container ID already exists.")
                    .build();
        }

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(cont.getId()).build();

        return Response.created(uri).build();

    }

    /*
        Not needed yet, Container is just ID for now.
    */
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void updateOrganization(@HeaderParam("authToken") String authToken,
//                                   @HeaderParam("tenantId") String tenantId,
//                                   @PathParam("name") String name, @Valid IntermodalContainer imc)
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
    public Response removeContainer(@HeaderParam("authToken") String authToken,
                                    @HeaderParam("tenantId") String tenantId,
                                    @PathParam("id") String id, @Valid IntermodalContainer imc)
                                        throws JSONException, IOException{

        try {
            imtdao = getDAO(authToken, tenantId);
        }catch (Exception e){
            logger.error("Get tenant DB failed.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errormessage",
                            "Get tenant DB failed. " + Arrays.toString(e.getStackTrace())))
                    .build();
        }

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

    private IntermodalContainerDAO getDAO(String authToken, String tenantId)
            throws JSONException, InvalidTenantIdException, InvalidTokenException, ClassNotFoundException {

        buildTenantDb(tenantId, authToken);
        return getTenantDb().onDemand(IntermodalContainerDAO.class);
    }


}
