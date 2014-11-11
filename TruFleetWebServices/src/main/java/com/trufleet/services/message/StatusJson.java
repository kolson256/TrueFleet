package com.trufleet.services.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Richard Morgan on 11/10/2014.
 */
public class StatusJson {
    private final String status, message;

    @JsonCreator
    public StatusJson(@JsonProperty("status") String status, @JsonProperty("message") String message){
        this.status = status;
        this.message = message;
    }

    public String getHeader() { return status; }

    public String getMessage() { return message; }
}
