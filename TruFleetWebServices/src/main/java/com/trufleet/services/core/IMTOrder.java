package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trufleet.services.core.util.ContactEntry;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by Richard on 11/12/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class IMTOrder {

    //In milliseconds from epoch
    @NotNull
    private long receiptTimestamp;

    //Likely to become Enum later
    private String orderType;

    //ID for TruFleet
    //AutoGenerate?
    private String internalID;

    //ID given by Client
    private String externalID;

    private String containerid;

    //Rail to become own object later?
    private String railLine;

    //Change to URI to contact resources?
    private ContactEntry pickupContact;
    private ContactEntry dropoffContact;

    //
    private DateTime deliveryWindowOpen;
    private DateTime deliveryWindowClose;

    public IMTOrder(String containerid, String orderType) {
        this.containerid = containerid;
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

    public ContactEntry getPickupContact() {
        return pickupContact;
    }

    public void setPickupContact(ContactEntry pickupContact) {
        this.pickupContact = pickupContact;
    }

    public ContactEntry getDropoffContact() {
        return dropoffContact;
    }

    public void setDropoffContact(ContactEntry dropoffContact) {
        this.dropoffContact = dropoffContact;
    }

    public DateTime getDeliveryWindowOpen() {
        return deliveryWindowOpen;
    }

    public void setDeliveryWindowOpen(DateTime deliveryWindowOpen) {
        this.deliveryWindowOpen = deliveryWindowOpen;
    }

    public DateTime getDeliveryWindowClose() {
        return deliveryWindowClose;
    }

    public void setDeliveryWindowClose(DateTime deliveryWindowClose) {
        this.deliveryWindowClose = deliveryWindowClose;
    }
}
