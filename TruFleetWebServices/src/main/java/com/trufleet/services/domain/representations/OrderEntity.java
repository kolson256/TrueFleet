package com.trufleet.services.domain.representations;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "order", schema = "public", catalog = "TruFleet")
public class OrderEntity {
    private int id;
    private String orderid;
    private String externalid;
    private String notes;
    private Timestamp receiptdate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "orderid")
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "externalid")
    public String getExternalid() {
        return externalid;
    }

    public void setExternalid(String externalid) {
        this.externalid = externalid;
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
    @Column(name = "receiptdate")
    public Timestamp getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Timestamp receiptdate) {
        this.receiptdate = receiptdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (externalid != null ? !externalid.equals(that.externalid) : that.externalid != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (orderid != null ? !orderid.equals(that.orderid) : that.orderid != null) return false;
        if (receiptdate != null ? !receiptdate.equals(that.receiptdate) : that.receiptdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
        result = 31 * result + (externalid != null ? externalid.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (receiptdate != null ? receiptdate.hashCode() : 0);
        return result;
    }
}
