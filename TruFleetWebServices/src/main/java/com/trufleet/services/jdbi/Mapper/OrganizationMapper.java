package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.representations.Organization;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class OrganizationMapper implements ResultSetMapper<Organization> {
    public Organization map(int index, ResultSet r, StatementContext ctx) throws SQLException
      {
        return new Organization(r.getString("name"), r.getString("tenantid"),
                r.getString("databaseurl"), r.getString("apiversion"));
      }
}
