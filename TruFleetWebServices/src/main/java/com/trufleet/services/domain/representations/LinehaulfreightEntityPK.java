package com.trufleet.services.domain.representations;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Richard on 2/6/2015.
 */
public class LinehaulfreightEntityPK implements Serializable {
    private int linehaulid;
    private int freightid;

    @Column(name = "linehaulid")
    @Id
    public int getLinehaulid() {
        return linehaulid;
    }

    public void setLinehaulid(int linehaulid) {
        this.linehaulid = linehaulid;
    }

    @Column(name = "freightid")
    @Id
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

        LinehaulfreightEntityPK that = (LinehaulfreightEntityPK) o;

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
