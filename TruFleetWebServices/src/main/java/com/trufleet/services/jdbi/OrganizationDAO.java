package com.trufleet.services.jdbi;

import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.Mapper.OrganizationMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
@RegisterMapper(OrganizationMapper.class)
public interface OrganizationDAO extends Transactional<AppUserDAO> {

    //find Organization by tenantid
        @SqlQuery("select name, tenantid, databaseurl, apiversion from organization where tenantid = :tenantid")
        public Organization findOrganizationbyTenantId(@Bind("tenantid") String tenantid);
}
