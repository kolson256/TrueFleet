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

    static Logger logger = LoggerFactory.getLogger(IMTOrderResource.class);
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final IMTOrderDAO orderDAO =  getAdminDb().onDemand(IMTOrderDAO.class);

    public IMTOrderResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @GET
    public Response getAllOrders( @HeaderParam("authToken") String authToken,
                                  @HeaderParam("tenantId") String tenantId){

        try {
            buildTenantDb(tenantId, authToken);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            new String("Tenant DB connection failed: " +e.getMessage()))).build();
        }
        return Response.ok(orderDAO.findAllOrders()).build();
    }

    @GET
    @Path("/{internalid}")
    public Response getOrderByInternalId( @HeaderParam("authToken") String authToken,
                                          @HeaderParam("tenantId") String tenantId,
                                          @PathParam("internalid") String id) {
        try {
            buildTenantDb(tenantId, authToken);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            new String("Tenant DB connection failed: " +e.getMessage()))).build();
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
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            new String("Tenant DB connection failed: " +e.getMessage()))).build();
        }

        IMTOrder order = imtorder;
        IMTOrder check = orderDAO.findOrderByContainerId(order.getContainerid());

        String uuid;
        //verify order with container does not already exist. Then call insert.
        //Verify that ordertype is not the same
        if ( null != check && !check.getContainerid().equalsIgnoreCase("") ) {
            uuid = orderDAO.insertOrder(order);


        }else if( !(check.getOrderType().equalsIgnoreCase( order.getOrderType() ) ) )
        {
            uuid = orderDAO.insertOrder(order);

        }else{
            return Response.status(Response.Status.CONFLICT)
                    .entity("Order with same Container and Order Type already exists.")
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
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            new String("Tenant DB connection failed: " +e.getMessage()))).build();
        }

        IMTOrder order = imtorder;
        IMTOrder check = orderDAO.findOrderByContainerId(order.getInternalID());

        //verify order with container does not already exist. Then call insert.
        if ( null == check ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Order with Internal ID of: " + order.getInternalID() +" not found." )
                    .build();
        }

        orderDAO.updateOrder(order);

        //return Response with URI to created resource.
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI orderUri = ub.path(order.getInternalID()).build();

        return Response.ok( orderUri ).build();

    }


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeorder( @HeaderParam("authToken") String authToken,
                                 @HeaderParam("tenantId") String tenantId,
                                 @Valid IMTOrder imtorder) throws JSONException, IOException {

        try {
            buildTenantDb(tenantId, authToken);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new StatusJson("errorMessage",
                            new String("Tenant DB connection failed: " +e.getMessage()))).build();
        }

        IMTOrder order = imtorder;
        IMTOrder check = orderDAO.findOrderByContainerId(order.getInternalID());

        //verify order with container does not already exist. Then call insert.
        if ( null == check ) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity( new StatusJson("errorMessage",
                             new String("Order with Internal ID of: " + order.getInternalID() +" not found." )))
                    .build();

        }

        orderDAO.removeOrder(order);

        if( null != orderDAO.findOrderByInternalId(order.getInternalID())){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new StatusJson("errorMessage",
                            new String("Order with Internal ID of: " + order.getInternalID() +" was not removed from Database." )))
                    .build();
        }else{
            return Response.ok().build();
        }
    }



}
