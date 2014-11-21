package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.Organization;
import com.trufleet.services.core.util.PhoneEntry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class PhoneEntryMapper implements ResultSetMapper<PhoneEntry> {
    public PhoneEntry map(int index, ResultSet r, StatementContext ctx) throws SQLException
      {
        return new PhoneEntry(r.getString("type"), r.getString("number"));

      }
}
