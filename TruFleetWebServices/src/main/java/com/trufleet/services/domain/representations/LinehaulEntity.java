package com.trufleet.services.domain.representations;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
@Entity
@Table(name = "linehaul", schema = "public", catalog = "trufleet")
public class LinehaulEntity {
    private int id;
    private String notes;
    private Timestamp shipdate;
    private Timestamp pickupstartdate;
    private Timestamp pickupenddate;
    private Timestamp deliverydeadline;


    private int orderid;
    private int shipperid;
    private int receiverid;
    private int terminalid;
    private int routeid;
    private int linehaulstatusid;


    public LinehaulEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "notes", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "shipdate", nullable = true, insertable = true, updatable = true)
    public Timestamp getShipdate() {
        return shipdate;
    }

    public void setShipdate(Timestamp shipdate) {
        this.shipdate = shipdate;
    }

    @Basic
    @Column(name = "pickupstartdate", nullable = true, insertable = true, updatable = true)
    public Timestamp getPickupstartdate() {
        return pickupstartdate;
    }

    public void setPickupstartdate(Timestamp pickupstartdate) {
        this.pickupstartdate = pickupstartdate;
    }

    @Basic
    @Column(name = "pickupenddate", nullable = true, insertable = true, updatable = true)
    public Timestamp getPickupenddate() {
        return pickupenddate;
    }

    public void setPickupenddate(Timestamp pickupenddate) {
        this.pickupenddate = pickupenddate;
    }

    @Basic
    @Column(name = "deliverydeadline", nullable = true, insertable = true, updatable = true)
    public Timestamp getDeliverydeadline() {
        return deliverydeadline;
    }

    public void setDeliverydeadline(Timestamp deliverydeadline) {
        this.deliverydeadline = deliverydeadline;
    }


    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getShipperid() {
        return shipperid;
    }

    public void setShipperid(int shipperid) {
        this.shipperid = shipperid;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    public int getLinehaulstatusid() {
        return linehaulstatusid;
    }

    public void setLinehaulstatusid(int linehaulstatusid) {
        this.linehaulstatusid = linehaulstatusid;
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
