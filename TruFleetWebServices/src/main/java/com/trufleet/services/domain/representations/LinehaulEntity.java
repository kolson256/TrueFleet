package com.trufleet.services.domain.representations;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "linehaul", schema = "public", catalog = "TruFleet")
public class LinehaulEntity {
    private int id;
    private String notes;
    private Timestamp shipdate;
    private Timestamp pickupstartdate;
    private Timestamp pickupenddate;
    private Timestamp deliverydeadline;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "shipdate")
    public Timestamp getShipdate() {
        return shipdate;
    }

    public void setShipdate(Timestamp shipdate) {
        this.shipdate = shipdate;
    }

    @Basic
    @Column(name = "pickupstartdate")
    public Timestamp getPickupstartdate() {
        return pickupstartdate;
    }

    public void setPickupstartdate(Timestamp pickupstartdate) {
        this.pickupstartdate = pickupstartdate;
    }

    @Basic
    @Column(name = "pickupenddate")
    public Timestamp getPickupenddate() {
        return pickupenddate;
    }

    public void setPickupenddate(Timestamp pickupenddate) {
        this.pickupenddate = pickupenddate;
    }

    @Basic
    @Column(name = "deliverydeadline")
    public Timestamp getDeliverydeadline() {
        return deliverydeadline;
    }

    public void setDeliverydeadline(Timestamp deliverydeadline) {
        this.deliverydeadline = deliverydeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinehaulEntity that = (LinehaulEntity) o;

        if (id != that.id) return false;
        if (deliverydeadline != null ? !deliverydeadline.equals(that.deliverydeadline) : that.deliverydeadline != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (pickupenddate != null ? !pickupenddate.equals(that.pickupenddate) : that.pickupenddate != null)
            return false;
        if (pickupstartdate != null ? !pickupstartdate.equals(that.pickupstartdate) : that.pickupstartdate != null)
            return false;
        if (shipdate != null ? !shipdate.equals(that.shipdate) : that.shipdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (shipdate != null ? shipdate.hashCode() : 0);
        result = 31 * result + (pickupstartdate != null ? pickupstartdate.hashCode() : 0);
        result = 31 * result + (pickupenddate != null ? pickupenddate.hashCode() : 0);
        result = 31 * result + (deliverydeadline != null ? deliverydeadline.hashCode() : 0);
        return result;
    }
}
