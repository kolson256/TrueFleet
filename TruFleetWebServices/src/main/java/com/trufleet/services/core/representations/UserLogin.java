package com.trufleet.services.core.representations;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserLogin {


    private String tenantId;

    private String username;

    private String password;


    public UserLogin() {
        // Jackson deserialization
    }

    public UserLogin(String username, String password, String tenantId) {
        this.username = username;
        this.password = password;
        this.tenantId = tenantId;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public String getTenantId() {
        return tenantId;
    }

    public String getPassword(){return password;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (tenantId != userLogin.tenantId) return false;
        if (!username.equals(userLogin.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tenantId.hashCode() ^ (tenantId.hashCode() >>> 32));
        result = 31 * result + username.hashCode();
        return result;
    }
}
