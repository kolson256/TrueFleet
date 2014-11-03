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

@Path("/org")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/x-www-form-urlencoded")
public class OrganizationResource {

    private final OrganizationDAO orgDAO;

    public OrganizationResource(OrganizationDAO orgDAO){
        this.orgDAO = orgDAO;
    }

    @GET
    public Organization query(@QueryParam("name") String name){
        return orgDAO.FindOrganizationByName(name);
    }

    @POST
    public void register(@FormParam("Name") String name){
        orgDAO.orgInsert(name, "testDbURL", "version-0.1");
    }


}
