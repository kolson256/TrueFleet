package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.IMTOrder;
import com.trufleet.services.jdbi.IMTOrderDAO;

import com.trufleet.services.message.StatusJson;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by Richard Morgan on 11/22/2014.
 */

@Path("/0.1/orders")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class IMTOrderResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(IMTOrderResource.class);
    //private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private IMTOrderDAO orderDAO;

    public IMTOrderResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @GET
    public Response getAllOrders( @HeaderParam("authToken") String authToken,
                                  @HeaderParam("tenantId") String tenantId){

        try {
            buildTenantDb(tenantId, authToken);
            orderDAO = getTenantDb().onDemand(IMTOrderDAO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            "Tenant DB connection failed: " + e.getMessage())).build();
        }
        return Response.ok(orderDAO.findAllOrders()).build();
    }

    @GET
    @Path ("/assigned")
    public IMTOrder getOrderByUsername() {

        //returning temporary order for now..
        return new IMTOrder(1416785460, "Pickup order", "123456", "23456", "123456", "Red railline", "Joseph Rails", "Joes Shop", "10/20/2014", "10/21/2014");
        //TODO: return real order, take username as argument/body to look up assigned order in DB
    }


    @GET
    @Path("/{internalid}")
    public Response getOrderByInternalId( @HeaderParam("authToken") String authToken,
                                          @HeaderParam("tenantId") String tenantId,
                                          @PathParam("internalid") String id) {
        try {
            buildTenantDb(tenantId, authToken);
            orderDAO = getTenantDb().onDemand(IMTOrderDAO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            "Tenant DB connection failed: " + e.getMessage())).build();
        }

        return Response.ok(orderDAO.findOrderByInternalId(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder( @HeaderParam("authToken") String authToken,
                                 @HeaderParam("tenantId") String tenantId,
                                 @Valid IMTOrder imtorder) throws JSONException, IOException {

        try {
            buildTenantDb(tenantId, authToken);
            orderDAO = getTenantDb().onDemand(IMTOrderDAO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            "Tenant DB connection failed: " + e.getMessage())).build();
        }

        IMTOrder check = orderDAO.findOrderByContainerId(imtorder.getContainerid());

        String uuid;
        //verify order with container does not already exist. Then call insert.
        if (check == null) {
            uuid = orderDAO.insertOrder(imtorder);

        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Order with Container ID of: " + imtorder.getContainerid() + " already exists.")
                    .build();
        }

        //return Response with URI to created resource.
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI orderUri = ub.path(uuid).build();

        return Response.created( orderUri ).build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder( @HeaderParam("authToken") String authToken,
                                 @HeaderParam("tenantId") String tenantId,
                                 @Valid IMTOrder imtorder) throws JSONException, IOException {

        try {
            buildTenantDb(tenantId, authToken);
            orderDAO = getTenantDb().onDemand(IMTOrderDAO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            "Tenant DB connection failed: " + e.getMessage())).build();
        }

        IMTOrder check = orderDAO.findOrderByContainerId(imtorder.getInternalID());

        //verify order with container does not already exist. Then call insert.
        if ( null == check ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Order with Internal ID of: " + imtorder.getInternalID() +" not found." )
                    .build();
        }

        orderDAO.updateOrder(imtorder);

        //return Response with URI to created resource.
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI orderUri = ub.path(imtorder.getInternalID()).build();

        return Response.ok( orderUri ).build();

    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeorder( @HeaderParam("authToken") String authToken,
                                 @HeaderParam("tenantId") String tenantId,
                                 @Valid IMTOrder imtorder) throws JSONException, IOException {

        try {
            buildTenantDb(tenantId, authToken);
            orderDAO = getTenantDb().onDemand(IMTOrderDAO.class);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            "Tenant DB connection failed: " + e.getMessage())).build();
        }

        IMTOrder check = orderDAO.findOrderByContainerId(imtorder.getInternalID());

        //verify order with container does not already exist. Then call insert.
        if ( null == check ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new StatusJson("errorMessage",
                            "Order with Internal ID of: " + imtorder.getInternalID() + " not found."))
                    .build();

        }

        orderDAO.removeOrder(imtorder);

        if( null != orderDAO.findOrderByInternalId(imtorder.getInternalID())){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errorMessage",
                            "Order with Internal ID of: " + imtorder.getInternalID() + " was not removed from Database."))
                    .build();
        }else{
            return Response.ok().build();
        }
    }



}
