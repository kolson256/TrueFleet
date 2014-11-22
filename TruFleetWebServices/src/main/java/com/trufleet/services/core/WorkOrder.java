package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Richard on 11/12/2014.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkOrder {

    private String internalOrderId;

    //Enum later?
    @JsonProperty("status")
    private String status;

    @JsonProperty("driver")
    private String driverId;

    @JsonCreator
    public WorkOrder(@JsonProperty("orderid") String internalOrderId){
        this.internalOrderId = internalOrderId;
    }

    public WorkOrder(String internalOrderId, String status, String driverId) {
        this.internalOrderId = internalOrderId;
        this.status = status;
        this.driverId = driverId;
    }

    public String getInternalOrderId() {
        return internalOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedDriver() {
        return driverId;
    }

    public void setAssignedDriver(DriverUser assignedDriver) {
        this.driverId = driverId;
    }
}
