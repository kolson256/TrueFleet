package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.ContainerLoad;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import io.dropwizard.jdbi.args.JodaDateTimeMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class LoadMapper implements ResultSetMapper<ContainerLoad> {

    public ContainerLoad map(int index, ResultSet r, StatementContext ctx) throws SQLException
      {
        return new ContainerLoad(
                r.getString("containerid"),
                r.getString("seal"),
                r.getInt("pieces"),
                r.getInt("weight"),
                r.getString("shipdate"));
      }
}
