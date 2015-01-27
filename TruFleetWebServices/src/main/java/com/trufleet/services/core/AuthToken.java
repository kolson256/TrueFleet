package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by Kyle Olson on 11/5/2014.
 */

public class AuthToken {

    private long appUserId;
    private String token;
    private Timestamp expirationDate;

    //public AuthToken(){}

    @JsonCreator
    public AuthToken(@JsonProperty("appuserid") long appUserId, @JsonProperty("token") String token,  @JsonProperty("timestamp") Timestamp expirationDate) {
        this.appUserId = appUserId;
        this.token = token;
        this.expirationDate = expirationDate;
    }



    public long getAppUserId() { return appUserId; }


    public String getToken() { return token; }


    public Timestamp getExpirationDate() { return expirationDate; }

}
