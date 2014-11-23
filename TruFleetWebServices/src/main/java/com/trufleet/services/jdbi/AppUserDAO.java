package com.trufleet.services.jdbi;

import com.trufleet.services.core.AppUser;
import com.trufleet.services.jdbi.mapper.AppUserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
@RegisterMapper(AppUserMapper.class)
public interface AppUserDAO extends Transactional<AppUserDAO> {

    //find User by username
    @SqlQuery("select id, username, firstname, lastname, registrationid from appuser where username = :user")
    public AppUser findAppUserbyUserName(@Bind("user") String username);

    //find User by Id
    @SqlQuery("select id, username, firstname, lastname, registrationid from appuser where id = :id")
    public AppUser findAppUserbyId(@Bind("id") long id);

    @SqlUpdate("insert into appuser (username, firstname, lastname, registrationid) values (:username, :firstName, :lastName, )")
    void insert(@Bind("username") String username, @Bind("firstName") String firstName, @Bind("lastName") String lastName);

    @SqlUpdate("update appuser set " +
            "registrationid = :registrationId " +
            "where username = :username")
    public int updateRegistrationId(@Bind("username") String username, @Bind("registrationId") String registrationId);

}
