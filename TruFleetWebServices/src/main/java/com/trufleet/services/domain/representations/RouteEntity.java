package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "route", schema = "public", catalog = "TruFleet")
public class RouteEntity {
    private int id;
    private String notes;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RouteEntity that = (RouteEntity) o;

        if (id != that.id) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}