package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.IntermodalContainer;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Richard Morgan on 11/19/2014.
 */
public class IntermodalContainerMapper implements ResultSetMapper<IntermodalContainer> {
    public IntermodalContainer map(int index, ResultSet r, StatementContext ctx) throws SQLException{
        return new IntermodalContainer(r.getString("id"));
    }

}
