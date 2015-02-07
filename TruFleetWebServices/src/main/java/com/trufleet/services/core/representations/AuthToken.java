package com.trufleet.services.core.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
public class AuthToken {

    private long appUserId;
    private String token;
    private Timestamp expirationDate;

    public AuthToken(long appUserId, String token, Timestamp expirationDate) {
        this.appUserId = appUserId;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    @JsonProperty
    public long getAppUserId() { return appUserId; }

    @JsonProperty
    public String getToken() { return token; }

    @JsonProperty
    public Timestamp getExpirationDate() { return expirationDate; }

}
