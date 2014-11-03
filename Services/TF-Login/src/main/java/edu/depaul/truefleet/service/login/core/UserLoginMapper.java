package edu.depaul.truefleet.service.login.core;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */

public class UserLoginMapper implements ResultSetMapper<UserLogin> {
    public UserLogin map(int index, ResultSet r, StatementContext ctx) throws SQLException
      {
        return new UserLogin(r.getString("username"), r.getString("password"), r.getString("orgid"));
      }
}
