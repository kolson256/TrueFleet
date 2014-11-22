package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.IMTOrder;
import com.trufleet.services.jdbi.IMTOrderDAO;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Richard Morgan on 11/22/2014.
 */

@Path("/0.1/orders")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class IMTOrderResource extends BaseResource {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final IMTOrderDAO orderDAO =  getAdminDb().onDemand(IMTOrderDAO.class);

    public IMTOrderResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    @GET
    public List<IMTOrder> getAllOrders(){
        return orderDAO.findAllOrders();
    }

    @GET
    @Path("/{internalid}")
    public IMTOrder getOrderByInternalId(@PathParam("internalid") String id) {
        return orderDAO.findOrderByInternalId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public IMTOrder createOrganization(@Valid IMTOrder imtorder) throws JSONException, IOException {

        IMTOrder order = imtorder;
        IMTOrder check = orderDAO.findOrderByContainerId(order.getContainerid());

        //verify order with container does not already exist. Then call insert.
        if ( null != check && !check.getContainerid().equalsIgnoreCase("") ) {
            orderDAO.insertOrganization(order);
        }
        //TODO add "else" Response

        //return Order
        return orderDAO.findOrderByContainerId(order.getContainerid());

    }




}
