package com.trufleet.services.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.OrganizationDAO;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by Richard on 11/8/2014.
 */

@Path("/0.1/org")
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class OrganizationResource extends BaseResource {


    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    private final OrganizationDAO orgdao =  getAdminDb().onDemand(OrganizationDAO.class);

    public OrganizationResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }


    //  Query for all Organizations in system.
    @GET
    public List<Organization> queryAllOrganizations() {
        return orgdao.findAllOrganizations();
    }


    @GET
    @Path("/{name}")
    public Organization queryOrganizationByName(@PathParam("name") String name) {
        return orgdao.findOrganizationByName(name);
    }

    /*
        Create an Organization

        return modified ORG JSON?
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createOrganization(@Valid Organization organization) throws JSONException, IOException{
        //Deserialize incoming JSON to Organization

        Organization org = organization;

        //verify org does not already exist. Then call insert.
        if (null == orgdao.findOrganizationByName(org.getName())) {
           orgdao.insertOrganization(org);
        }

        //TODO:  Create a return message?
    }

    /*
        Update an Organization.

     */
    @PUT  @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrganization(@PathParam("name") String name, @Valid Organization organization)
            throws JSONException, IOException{

        //verify org named exists, get that object.
        Organization checkOrg = orgdao.findOrganizationByName(name);

        if( null != checkOrg ) {
            Organization org = organization;
            orgdao.updateOrganization(org);
        }
        //TODO: compare existing to request to verify that there is a change?

        //TODO:  Create a return message?

    }

    /*
       Delete an Organization.

       Should this have a return?
    */
    @DELETE @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeOrganization(@PathParam("name") String name, @Valid Organization organization)
            throws JSONException, IOException{

        //verify that org exists
        Organization checkOrg = orgdao.findOrganizationByName(name);

        if( null != checkOrg ) {
            Organization org = organization;
            orgdao.removeOrganization(org);
        }
        //TODO: compare existing to request to verify that there is a change?

        //TODO:  Create a return message?
    }



}
