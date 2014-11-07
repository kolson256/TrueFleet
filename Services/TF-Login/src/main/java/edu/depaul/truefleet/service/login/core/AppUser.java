package edu.depaul.truefleet.service.login.core;

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

    public AppUser(long id, String userName, String firstName, String lastName) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty
    public long getId() { return id; }

    @JsonProperty
    public String getUserName() { return userName; }

    @JsonProperty
    public String getFirstName() { return firstName; }

    @JsonProperty
    public String getLastName() { return lastName; }

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
