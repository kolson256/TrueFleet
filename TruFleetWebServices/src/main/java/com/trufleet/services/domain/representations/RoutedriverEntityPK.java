package com.trufleet.services.domain.representations;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Richard on 2/6/2015.
 */
public class RoutedriverEntityPK implements Serializable {
    private int routeid;
    private int driverid;

    @Column(name = "routeid")
    @Id
    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    @Column(name = "driverid")
    @Id
    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutedriverEntityPK that = (RoutedriverEntityPK) o;

        if (driverid != that.driverid) return false;
        if (routeid != that.routeid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = routeid;
        result = 31 * result + driverid;
        return result;
    }
}
