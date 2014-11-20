package com.trufleet.services.jdbi;

import com.trufleet.services.core.IntermodalContainer;
import com.trufleet.services.jdbi.mapper.IntermodalContainerMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

/**
 * Created by Richard Morgan on 11/19/2014.
 */
@RegisterMapper(IntermodalContainerMapper.class)
public interface IntermodalContainerDAO extends Transactional<IntermodalContainerDAO>{

    @SqlQuery("select * from intermodalcontainer")
    public List<IntermodalContainer> findAllContainers();

    @SqlQuery("select * from intermodalcontainer where id = :id")
    public IntermodalContainer findContainerById(@Bind("id") String id);

    @SqlUpdate("insert into intermodalcontainer id = :id")
    public void insertContainer(@BindBean IntermodalContainer container);


    //right now, there is only ID field, and you will not be able to "update" it.
/*    @SqlUpdate("update intermodalcontainer set [insert furture fields here]]where id = :id")
    public void updateContainer(@BindBean IntermodalContainer container);*/

    @SqlUpdate("delete * from intermodalcontainer where id = :id")
    public void removeContainer(@BindBean IntermodalContainer container);


}
