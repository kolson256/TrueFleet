package com.trufleet.services.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 * Created by Richard on 11/12/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class IMTOrder {

    //In milliseconds from epoch
    @NotNull
    private long receiptTimestamp;

    //Likely to become Enum later
    private String orderType;

    //ID for TruFleet
    private String internalID;

    //ID given by Client
    private String externalID;

    private IntermodalContainer container;

    //Rail to become own object later?
    private String railLine;


    private ContactEntry pickup;
    private ContactEntry dropoff;

    private DateTime windowStart;
    private DateTime windowClose;

    public IMTOrder(String containerid, String orderType) {
        this.container = new IntermodalContainer(containerid);
        this.orderType = orderType;
        this.receiptTimestamp = System.currentTimeMillis();
    }

    public long getReceiptTimestamp() {
        return receiptTimestamp;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getInternalID() {
        return internalID;
    }

    public void setInternalID(String internalID) {
        this.internalID = internalID;
    }

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    public IntermodalContainer getContainer() {
        return container;
    }

    public void setContainer(IntermodalContainer container) {
        this.container = container;
    }

    public String getRailLine() {
        return railLine;
    }

    public void setRailLine(String railLine) {
        this.railLine = railLine;
    }

    public ContactEntry getPickup() {
        return pickup;
    }

    public void setPickup(ContactEntry pickup) {
        this.pickup = pickup;
    }

    public ContactEntry getDropoff() {
        return dropoff;
    }

    public void setDropoff(ContactEntry dropoff) {
        this.dropoff = dropoff;
    }

    public DateTime getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(DateTime windowStart) {
        this.windowStart = windowStart;
    }

    public DateTime getWindowClose() {
        return windowClose;
    }

    public void setWindowClose(DateTime windowClose) {
        this.windowClose = windowClose;
    }
}
