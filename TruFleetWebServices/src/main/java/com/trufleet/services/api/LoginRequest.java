package com.trufleet.services.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Richard Morgan on 12/8/2014.
 */


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginRequest {

    private String username;
    private String password;

    @JsonProperty("remember")
    private boolean rememberMe;


    public LoginRequest(){}

    public LoginRequest(String user, String pass){
        username = user;
        password = pass;
    }
    public LoginRequest(String user, String pass, boolean remember){
        username = user;
        password = pass;
        rememberMe = remember;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
