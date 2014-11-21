package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.Organization;
import com.trufleet.services.core.util.ContactEntry;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class ContactMapper implements ResultSetMapper<ContactEntry> {
    public ContactEntry map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new ContactEntry(r.getString("id"), r.getString("name"), r.getString("address"), r.getString("phone"), r.getString("notes"));
    }
}
