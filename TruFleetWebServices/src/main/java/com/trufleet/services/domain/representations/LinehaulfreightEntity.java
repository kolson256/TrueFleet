package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "linehaulfreight", schema = "public", catalog = "TruFleet")
@IdClass(LinehaulfreightEntityPK.class)
public class LinehaulfreightEntity {
    private int linehaulid;
    private int freightid;

    @Id
    @Column(name = "linehaulid")
    public int getLinehaulid() {
        return linehaulid;
    }

    public void setLinehaulid(int linehaulid) {
        this.linehaulid = linehaulid;
    }

    @Id
    @Column(name = "freightid")
    public int getFreightid() {
        return freightid;
    }

    public void setFreightid(int freightid) {
        this.freightid = freightid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinehaulfreightEntity that = (LinehaulfreightEntity) o;

        if (freightid != that.freightid) return false;
        if (linehaulid != that.linehaulid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = linehaulid;
        result = 31 * result + freightid;
        return result;
    }
}
