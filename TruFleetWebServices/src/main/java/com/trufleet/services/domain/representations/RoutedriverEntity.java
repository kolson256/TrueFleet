package com.trufleet.services.domain.representations;

import javax.persistence.*;

/**
 * Created by Richard on 2/6/2015.
 */
@Entity
@Table(name = "routedriver", schema = "public", catalog = "TruFleet")
@IdClass(RoutedriverEntityPK.class)
public class RoutedriverEntity {
    private int routeid;
    private int driverid;

    @Id
    @Column(name = "routeid")
    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    @Id
    @Column(name = "driverid")
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

        RoutedriverEntity that = (RoutedriverEntity) o;

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
