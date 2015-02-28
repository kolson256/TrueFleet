package app.truefleet.com.truefleet.Tasks;

import org.json.JSONObject;

import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Tasks.Requests.GcmRegisterRequest;
import app.truefleet.com.truefleet.Tasks.Requests.LoginRequest;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public interface ApiService {
    @Headers({
            "Content-type: application/json",
            "Accept: application/json"
    })
    @POST("/Login")
    public void login(@Body LoginRequest loginRequest, RestCallback<User> cb);

    @Headers({
            "Content-type: application/json",
            "Accept: application/json"
    })
    @POST("/GcmRegistration")
    public void registerGcmWithServer(@Header("authToken") String authToken, @Header("tenantId") String tenantId,
                                      @Body GcmRegisterRequest gcmRegisterRequest, RestCallback<JSONObject> cb);
}
