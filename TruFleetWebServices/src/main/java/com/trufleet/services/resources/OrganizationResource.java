package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.OrganizationDAO;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Richard on 11/8/2014.
 */

@Path("/org")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/x-www-form-urlencoded")
public class OrganizationResource extends BaseResource {

    private final OrganizationDAO orgdao =  getAdminDb().onDemand(OrganizationDAO.class);

    public OrganizationResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        super(adminDBI, configuration, environment);
    }

    /*
      Query for all Organizations in system.
   */
    @GET
    public List<Organization> queryAllOrganizations(){


        return null;
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
    public void createOrganization(String json){

        //verify org does not already exist. Then call insert.
        //orgDAO.insertOrganization();
    }

    /*
        Update an Organization.

     */
    @PUT  @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrganization(@PathParam("name") String name, String json) {
        //verify org named exists, get that object.
        //verify that there is a change, if so commit it, if not, do nothing.



    }

    /*
       Delete an Organization.

       Should this have a return?
    */
    @DELETE @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeOrganization(@PathParam("name") String name, String json){

        //verify that org exists as requested, if same then delete.

    }



}
