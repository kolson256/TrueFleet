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
    private DriverUser assignedDriver;

    @JsonCreator
    public WorkOrder(@JsonProperty("orderid") String internalOrderId){
        this.internalOrderId = internalOrderId;
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

    public DriverUser getAssignedDriver() {
        return assignedDriver;
    }

    public void setAssignedDriver(DriverUser assignedDriver) {
        this.assignedDriver = assignedDriver;
    }
}
