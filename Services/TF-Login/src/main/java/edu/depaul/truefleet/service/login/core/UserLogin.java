package edu.depaul.truefleet.service.login.core;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserLogin {

    @JsonProperty("organizationid")
    private String organizationId;
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    public UserLogin() {
        // Jackson deserialization
    }

    public UserLogin(String username, String password, String orgID) {
        this.username = username;
        this.password = password;
        this.organizationId = orgID;
    }

    public String getUsername() {
        return username;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getPassword(){return password;}

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (organizationId != null ? !organizationId.equals(userLogin.organizationId) : userLogin.organizationId != null)
            return false;
        if (!password.equals(userLogin.password)) return false;
        if (!username.equals(userLogin.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = organizationId != null ? organizationId.hashCode() : 0;
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
