package app.truefleet.com.truefleet.Models;


public class User {
    private String apiVersion;
    private String tenantId;
    private String authenticationToken;
    private String username;

    private User() {
        //Dummy constructor to please jackson
    }
    public User(String apiVersion, String tenantId, String authenticationToken, String username) {

        this.username = username;
        this.apiVersion = apiVersion;
        this.tenantId = tenantId;
        this.authenticationToken = authenticationToken;

    }

    public String getUsername() {
        return username;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getauthenticationToken() {
        return authenticationToken;
    }
}
