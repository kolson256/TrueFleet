package app.truefleet.com.truefleet.Tasks.Requests;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class LoginRequest {
    final String username;
    final String password;

    public LoginRequest(String username, String password) {

        this.username = username;
        this.password = password;
    }
}
