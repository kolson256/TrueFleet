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

import com.trufleet.services.core.representations.Organization;
import com.trufleet.services.jdbi.mapper.OrganizationMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

/**
 * Created by Richard Morgan on 11/2/2014.
 */


@RegisterMapper(OrganizationMapper.class)
public interface OrganizationDAO extends Transactional<OrganizationDAO>{

    @SqlQuery("select * from organization")
    public List<Organization> findAllOrganizations();

    @SqlQuery("select * from organization where name = :name")
    public Organization findOrganizationByName(@Bind("name") String name);

    //find Organization by tenantid
    @SqlQuery("select * from  organization where tenantid = :tenantid")
    public Organization findOrganizationbyTenantId(@Bind("tenantid") String tenantid);

    @SqlUpdate("insert into organization (name, databaseurl, apiversion) values (:name, :databaseURL, :apiVersion)")
    @GetGeneratedKeys
    public String insertOrganization(@BindBean Organization organization);

    /* Note: COALESCE will return whichever value is not null, before entering a null.
        This allows an Organization object that contains null values for some values to be used to update
        an existing Organization.  For example if a JSON object does not have the tenantID, we will not overwrite it with null
    */
    @SqlUpdate("update organization set " +
            "tenantid = COALESCE( :tenantID, tenantid), " +
            "databaseurl = COALESCE( :databaseURL, databaseurl), " +
            "apiversion = COALESCE( :apiVersion, apiversion) " +
            "where name = :name")
    public int updateOrganization(@BindBean Organization org);

    @SqlUpdate("delete from organization * where name = :name")
    public void removeOrganization(@BindBean Organization org);

}
