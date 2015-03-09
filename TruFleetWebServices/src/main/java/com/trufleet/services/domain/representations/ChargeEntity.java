package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard Morgan on 2/9/2015.
 */
@Entity
@Table(name = "charge", schema = "public", catalog = "trufleet")
public class ChargeEntity {
    private int id;
    private String description;
    private Double amount;

    private int orderid;

    public ChargeEntity() {
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
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "amount", nullable = true, insertable = true, updatable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargeEntity that = (ChargeEntity) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
