package app.truefleet.com.truefleet.Tasks.Requests;

/**
 * Created by Chris Lacy on 2/11/2015.
 */
public class GcmRegisterRequest {
    final String username;
    final String registrationId;

    public GcmRegisterRequest(String username, String registrationId ) {

        this.username = username;
        this.registrationId = registrationId;
    }
}
