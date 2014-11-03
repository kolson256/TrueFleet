package edu.depaul.truefleet.service.login.core;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserLogin {


    private String organizationId;

    private String username;

    private String password;

    public UserLogin() {
        // Jackson deserialization
    }

    public UserLogin(String username, String password, String orgID) {
        this.username = username;
        this.password = password;
        this.organizationId = orgID;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public String getOrganizationId() {
        return organizationId;
    }

    public String getPassword(){return password;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (organizationId != userLogin.organizationId) return false;
        if (!username.equals(userLogin.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (organizationId.hashCode() ^ (organizationId.hashCode() >>> 32));
        result = 31 * result + username.hashCode();
        return result;
    }
}
