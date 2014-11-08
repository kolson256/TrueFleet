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

package com.trufleet.services.jdbi;

import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.mapper.OrganizationMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * Created by Richard Morgan on 11/2/2014.
 */



public interface OrganizationDAO {

    @SqlQuery("select * from organization")
    @Mapper(OrganizationMapper.class)
    public List<Organization> findAllOrganizations();

    @SqlQuery("select * from organization where name = :name")
    @Mapper(OrganizationMapper.class)
    public Organization findOrganizationByName(@Bind("name") String name);

    //find Organization by tenantid
    @SqlQuery("select * from  organization where tenantid = :tenantid")
    @Mapper(OrganizationMapper.class)
    public Organization findOrganizationbyTenantId(@Bind("tenantid") String tenantid);

    @SqlUpdate("insert into organization (name, databaseurl, apiversion) values (:name, :db, :api)")
    @GetGeneratedKeys
    public String insertOrganization(@Bind("name") String name, @Bind("db") String dbURL, @Bind("api") String apiVersion);

    @SqlUpdate("update organization set (name, databaseurl, apiversion) values (:name, :databaseURL, :apiVersion) where tenantid = :tenantID")
    public int updateOrganization(@BindBean Organization org);

    @SqlUpdate("delete from organization * where name = :name")
    public void removeOrganization(@BindBean Organization org);

}
