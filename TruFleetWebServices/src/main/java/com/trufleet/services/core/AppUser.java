package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Kyle Olson on 11/3/2014.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AppUser {

    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String registrationId;

    public AppUser(long id, String userName, String firstName, String lastName, String registrationId) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationId = registrationId;
    }

    @JsonProperty
    public long getId() { return id; }

    @JsonProperty
    public String getUserName() { return userName; }

    @JsonProperty
    public String getFirstName() { return firstName; }

    @JsonProperty
    public String getLastName() { return lastName; }

    @JsonProperty
    public String getRegistrationId() { return registrationId;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;

        if (userName != appUser.userName) return false;
        if (!userName.equals(appUser.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }
}
