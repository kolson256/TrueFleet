package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Richard on 11/12/2014.
 */
public class DriverUser  {

    @NotNull
    private String driverID;

    //Android application ID
    @JsonProperty
    private String applicationID;

    @Valid
    @NotNull
    private AppUser appUser;

    @JsonCreator
    public DriverUser(@JsonProperty("user") AppUser appUser,@JsonProperty("driverid") String driverID) {
        this.appUser = appUser;
        this.driverID = driverID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

}
