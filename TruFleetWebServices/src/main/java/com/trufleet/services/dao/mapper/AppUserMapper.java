package com.trufleet.services.dao.mapper;

import com.trufleet.services.core.representations.AppUser;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class AppUserMapper implements ResultSetMapper<AppUser> {
    public AppUser map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new AppUser(r.getLong("id"), r.getString("username"), r.getString("firstname"), r.getString("lastname"), r.getString("registrationid"));
    }
}
