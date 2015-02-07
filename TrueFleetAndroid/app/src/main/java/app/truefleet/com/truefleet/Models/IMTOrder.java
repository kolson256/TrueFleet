package app.truefleet.com.truefleet.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String status;
    //ID for TruFleet
    //AutoGenerate?
    private String internalID;

    //ID given by Client
    private String externalID;

    private String container;

    //Rail to become own object later?
    private String railLine;

    //Change to URI to contact resources?
    private String pickupContact;
    private String dropoffContact;

    //
    private String deliveryWindowOpen;
    private String deliveryWindowClose;



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

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
    public long getReceiptTimestamp() {
        return receiptTimestamp;
    }

    public void setReceiptTimestamp(long timestamp) { this.receiptTimestamp = timestamp; }
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

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Date d = new Date(receiptTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

        result.append(format.format(d) + " - " + orderType + " - " + status);

        return result.toString();
    }
}
