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

package edu.depaul.truefleet.service.login.dao;

import edu.depaul.truefleet.service.login.core.*;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Richard Morgan on 11/2/2014.
 */
public interface UserLoginDAO {

    //find User by username
    @SqlQuery("select username, password, organizationid from userlogin where username = :user")
    @Mapper(UserLoginMapper.class)
    public UserLogin FindUserLoginbyUserName(@Bind("user") String username);

    //find Organization by tenantid
    @SqlQuery("select name, tenantid, databaseurl, apiversion from organization where tenantid = :tenantid")
    @Mapper(OrganizationMapper.class)
    public Organization FindOrganizationbyTenantId(@Bind("tenantid") String tenantid);

    //find User by username
    @SqlQuery("select id, username, firstname, lastname from appuser where username = :user")
    @Mapper(AppUserMapper.class)
    public AppUser FindAppUserbyUserName(@Bind("user") String username);

    @SqlUpdate("insert into authtoken (appuserid, token, expirationdate) values (:id, :token, :expdate)")
    void insertToken(@Bind("id") long id, @Bind("token") String token, @Bind("expdate") Timestamp expirationDate);

    @SqlUpdate("insert into userlogin (username, password, organizationid) values (:name, :pw, :orgid)")
    void insert(@Bind("name") String name, @Bind("pw") String pw, @Bind("orgid") long orgid);


}
