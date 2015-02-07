package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.representations.AuthToken;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
public class AuthTokenMapper implements ResultSetMapper<AuthToken> {
    public AuthToken map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        return new AuthToken(r.getLong("appuserid"), r.getString("token"), r.getTimestamp("expirationDate"));
    }
}