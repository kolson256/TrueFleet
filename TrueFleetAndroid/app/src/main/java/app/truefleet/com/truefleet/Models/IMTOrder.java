package app.truefleet.com.truefleet.Models;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
/**
 * Created by Chris Lacy on 11/19/2014.
 */
public class IMTOrder {
    private static volatile IMTOrder instance = null;


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

    private String container;

    //Rail to become own object later?
    private String railLine;

    //Change to URI to contact resources?
    private ContactEntry pickupContact;
    private ContactEntry dropoffContact;

    //
    private DateTime deliveryWindowOpen;
    private DateTime deliveryWindowClose;

    private String orderStatus;

    public static IMTOrder getInstance() {
        if (instance == null) {
            synchronized (IMTOrder.class) {
                if (instance == null) {
                    instance = new IMTOrder();
                }
            }
        }
        return instance;
    }
 //   public IMTOrder(String containerid, String orderType) {
  //      this.container = containerid;
   //     this.orderType = orderType;
    //    this.receiptTimestamp = System.currentTimeMillis();
    //}

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

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
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
    public String getOrderStatus() { return this.orderStatus; }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(receiptTimestamp + " - " + orderType);

        return result.toString();
    }
}
