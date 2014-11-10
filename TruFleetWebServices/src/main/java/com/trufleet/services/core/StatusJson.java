package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Richard Morgan on 11/10/2014.
 */
public class StatusJson {
    private final String header, message;

    @JsonCreator
    public StatusJson(@JsonProperty("header") String header, @JsonProperty("message") String message){
        this.header = header;
        this.message = message;
    }

    public String getHeader() { return header; }

    public String getMessage() { return message; }
}
