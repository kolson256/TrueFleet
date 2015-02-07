package com.trufleet.services.jdbi.mapper;

import com.trufleet.services.core.IMTOrder;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
public class IMTOrderMapper implements ResultSetMapper<IMTOrder> {
    public IMTOrder map(int index, ResultSet r, StatementContext ctx) throws SQLException
      {
        return new IMTOrder(
                r.getLong("receipttime"),
                r.getString("ordertype"),
               r.getString("internalid"),
                r.getString("externalid"),
                r.getString("containerid"),
                r.getString("railline"),
                r.getString("pickupcontact"),
                r.getString("dropoffcontact"),
                r.getString("deliverywindowopen"),
                r.getString("deliverywindowclose"));
      }
}
