/*
 * Copyright (c) 2014. Richard Morgan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.depaul.truefleet.service.login.resources;

/**
 * Created by Richard Morgan on 11/2/2014.
 */

import edu.depaul.truefleet.service.login.core.Organization;
import edu.depaul.truefleet.service.login.dao.OrganizationDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/org")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/x-www-form-urlencoded")
public class OrganizationResource {

    private final OrganizationDAO orgDAO;

    public OrganizationResource(OrganizationDAO orgDAO){
        this.orgDAO = orgDAO;
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
        return orgDAO.findOrganizationByName(name);
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

        return modified Org JSON?

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
