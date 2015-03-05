package app.truefleet.com.truefleet.Tasks;

import com.squareup.okhttp.Route;

import org.json.JSONObject;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Tasks.Requests.GcmRegisterRequest;
import app.truefleet.com.truefleet.Tasks.Requests.LoginRequest;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

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

    @POST("/0.1/order/{id}")
    public void getOrder(@Path("id") int orderId, RestCallback<Order> cb);

    @POST("/0.1/order/{id}")
    public void getAccount(@Path("id") int accountId, RestCallback<Account> cb);

    @POST("/0.1/order/{id}")
    public void getContainer(@Path("id") int containerId, RestCallback<Container> cb);

    @POST("/0.1/route/{id}")
    public void getRoute(@Path("id") int routeId, RestCallback<Route> cb);
}
