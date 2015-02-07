package app.truefleet.com.truefleet.Models;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
public class Linehaul {
    private int id;

    private int orderId;
    private int externalId;
    private int routeId;
    private int shipdate;
    private int pickupStartDate;
    private int pickupEndDate;
    private int deliveryDeadline;

    public int getShipdate() {
        return shipdate;
    }

    public void setShipdate(int shipdate) {
        this.shipdate = shipdate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getPickupStartDate() {
        return pickupStartDate;
    }

    public void setPickupStartDate(int pickupStartDate) {
        this.pickupStartDate = pickupStartDate;
    }

    public int getPickupEndDate() {
        return pickupEndDate;
    }

    public void setPickupEndDate(int pickupEndDate) {
        this.pickupEndDate = pickupEndDate;
    }

    public int getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(int deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }
}
