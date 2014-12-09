package com.trufleet.services.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.trufleet.services.api.enums.StatusEnum;

/**
 * Created by Richard Morgan on 12/8/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginResponse {

    @JsonProperty("authenticationtoken")
    private String authenticationToken;
    @JsonProperty("tenantid")
    private String tenantID;
    @JsonProperty("apiversion")
    private String apiVersion;
    private StatusEnum status;
    private String message;

    public LoginResponse(){}

    public LoginResponse(StatusEnum status, String message){
        this.status = status;
        this.message = message;
    }


    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
