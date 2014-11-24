package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;

/**
 * Created by Richard on 11/12/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class IMTOrder {

    //In milliseconds from epoch
    private long receiptTimestamp;

    //Likely to become Enum later
    @NotNull
    private String orderType;

    //ID for TruFleet
    //AutoGenerate?
    private String internalID;

    //ID given by Client
    private String externalID;

    @NotNull
    private String containerid;

    //Rail to become own object later?
    private String railLine;

    //TODO: Change Data type later.
    //Change to URI to contact resources?
    private String pickupContact;
    private String dropoffContact;

    //TODO: Change Data type later.
    private String deliveryWindowOpen;
    private String deliveryWindowClose;

    public IMTOrder(){}

    public IMTOrder(String containerid, String orderType) {
        this.containerid = containerid;
        this.orderType = orderType;
        this.receiptTimestamp = System.currentTimeMillis();
    }

/*
    public IMTOrder(long receiptTimestamp, String orderType, String internalID, String externalID, String containerid, String railLine, String pickupContact, String dropoffContact, String deliveryWindowOpen, String deliveryWindowClose) {
        this.receiptTimestamp = receiptTimestamp;
        this.orderType = orderType;
        this.internalID = internalID;
        this.externalID = externalID;
        this.containerid = containerid;
        this.railLine = railLine;
        this.pickupContact = pickupContact;
        this.dropoffContact = dropoffContact;
        this.deliveryWindowOpen = deliveryWindowOpen;
        this.deliveryWindowClose = deliveryWindowClose;
    }
*/

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

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid;
    }

    public String getRailLine() {
        return railLine;
    }

    public void setRailLine(String railLine) {
        this.railLine = railLine;
    }

    public String getPickupContact() {
        return pickupContact;
    }

    public void setPickupContact(String pickupContact) {
        this.pickupContact = pickupContact;
    }

    public String getDropoffContact() {
        return dropoffContact;
    }

    public void setDropoffContact(String dropoffContact) {
        this.dropoffContact = dropoffContact;
    }

    public String getDeliveryWindowOpen() {
        return deliveryWindowOpen;
    }

    public void setDeliveryWindowOpen(String deliveryWindowOpen) {
        this.deliveryWindowOpen = deliveryWindowOpen;
    }

    public String getDeliveryWindowClose() {
        return deliveryWindowClose;
    }

    public void setDeliveryWindowClose(String deliveryWindowClose) {
        this.deliveryWindowClose = deliveryWindowClose;
    }
}
