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

import edu.depaul.truefleet.service.login.core.UserLogin;
import edu.depaul.truefleet.service.login.dao.UserLoginDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Richard Morgan on 11/2/2014.
 */

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRegisterResource {

    private final UserLoginDAO dao;

    public UserRegisterResource(UserLoginDAO dao){
        this.dao = dao;
    }

    @GET
    public UserLogin query(@QueryParam("name") String name){
        return dao.findUserLoginbyUserName(name);
    }

    @POST
    public void register(@FormParam("username") String name, @FormParam("password") String password, @FormParam("orgID") long orgid){
        dao.insert( name, password, orgid);
    }
}
