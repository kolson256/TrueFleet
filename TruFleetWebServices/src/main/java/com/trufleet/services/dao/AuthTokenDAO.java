package com.trufleet.services.dao;

import com.trufleet.services.core.representations.AuthToken;
import com.trufleet.services.dao.mapper.AuthTokenMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.sql.Timestamp;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
@RegisterMapper(AuthTokenMapper.class)
public interface AuthTokenDAO extends Transactional<AppUserDAO> {

    //find User by username
    @SqlQuery("select appuserid, token, expirationDate from authtoken where token = :token")
    public AuthToken findAuthToken(@Bind("token") String token);

    //find User by username
    @SqlQuery("select appuserid, token, expirationDate from authtoken where appuserid = :userid")
    public AuthToken findAuthTokenByUserId(@Bind("userid") long userId);

    @SqlUpdate("insert into authtoken (appuserid, token, expirationdate) values (:id, :token, :expdate)")
    void insertToken(@Bind("id") long id, @Bind("token") String token, @Bind("expdate") Timestamp expirationDate);
}
