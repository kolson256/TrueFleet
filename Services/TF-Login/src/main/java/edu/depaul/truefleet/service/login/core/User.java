package edu.depaul.truefleet.service.login.core;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class User {
    private long id;

    private Organization company;
    private String email;
    private String password;

    private String displayName;

    //private Role role;


    public User() {
        // Jackson deserialization
    }

    public User(long id, String email) {
        this.id = id;
        this.email = email;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }


}