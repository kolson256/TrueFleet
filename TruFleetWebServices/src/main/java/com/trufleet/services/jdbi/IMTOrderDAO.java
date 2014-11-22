package com.trufleet.services.jdbi;

/**
 * Created by Richard Morgan on 11/21/2014.
 */

import com.trufleet.services.core.IMTOrder;
import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.mapper.IMTOrderMapper;
import com.trufleet.services.jdbi.mapper.OrganizationMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.mixins.Transactional;

import java.util.List;

@RegisterMapper(IMTOrderMapper.class)
public interface IMTOrderDAO extends Transactional<IMTOrderDAO>{

    @SqlQuery("select * from imtorder")
    public List<IMTOrder> findAllOrders();

    @SqlQuery("select * from imtorder where internalid = :internalid")
    public IMTOrder findOrderByInternalId(@Bind("internalid") String internalid);

    @SqlQuery("select * from imtorder where externalid = :externalid")
    public IMTOrder findOrderByExternalId(@Bind("externalid") String externalid );

    //find
    @SqlQuery("select * from  imtorder where tenantid = :tenantid")
    public IMTOrder findOrderByContainerId(@Bind("containerid") String containerid);

    @SqlUpdate("insert into imtorder (containerid, ordertype, receipttime) values (:containerid, :orderType, :receiptTimestamp)")
    @GetGeneratedKeys
    public String insertOrganization(@BindBean IMTOrder order);

    /* Note: COALESCE will return whichever value is not null, before entering a null.
        This allows an Organization object that contains null values for some values to be used to update
        an existing Organization.  For example if a JSON object does not have the tenantID, we will not overwrite it with null
    */
    @SqlUpdate("update imtorder set " +
            "ordertype = COALESCE( :orderType, ordertype), " +
            "externalid = COALESCE( :externalID, externalid), " +
            "containerid = COALESCE( :containerid, containerid) " +
            "railline = COALESCE( :railLine, railline) " +
            "pickupcontact = COALESCE( :pickupContact, pickupcontact) " +
            "dropoffcontact = COALESCE( :pickupContact, dropoffcontact) " +
            "deliverywindowopen = COALESCE( :deliveryWindowOpen, deliverywindowopen) " +
            "deliverywindowclosed = COALESCE( :deliveryWindowOpen, deliverywindowclosed) " +
            "where internalid = :internalID")
    public int updateOrganization(@BindBean IMTOrder order);

    @SqlUpdate("delete from imtorder * where internalid = :internalid")
    public void removeOrganization(@BindBean IMTOrder order);



}
