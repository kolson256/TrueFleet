package app.truefleet.com.truefleet.Tasks;

import com.squareup.okhttp.Route;

import org.json.JSONObject;

import java.util.List;

import app.truefleet.com.truefleet.Models.Account;
import app.truefleet.com.truefleet.Models.Contact;
import app.truefleet.com.truefleet.Models.Container;
import app.truefleet.com.truefleet.Models.Freight;
import app.truefleet.com.truefleet.Models.Linehaul;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Tasks.Requests.GcmRegisterRequest;
import app.truefleet.com.truefleet.Tasks.Requests.LoginRequest;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
//TODO: Add POST status
 //TODO: Add POST picture
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


    //Synchronous - need to get in order
    @GET("/0.1/order/{id}")
    public Order getOrder(@Path("id") int orderId);

    @GET("/0.1/account/{id}")
    public Account getAccount(@Path("id") int accountId);

    @GET("/0.1/container/{id}")
    public Container getContainer(@Path("id") int containerId);

    @GET("/0.1/route/{id}")
    public Route getRoute(@Path("id") int routeId);

    @GET("/0.1/linehaul/{id}")
    public Linehaul getLinehaul(@Path("id") int routeId);

    @GET("/0.1/freight/{id}")
    public Freight getFreight(@Path("id") int routeId);

    @GET("/0.1/contact/{id}")
    public Contact getContact(@Path("id") int routeId);

    //------- not implemented on server ------------------------------
    @GET("/0.1/linehauls/{routeid}/{orderid}")
    public List<Linehaul> getLinehauls(@Path("routeid") int routeid, @Path("orderid") int orderid);

    @GET("/0.1/freights/{linehaulid}")
    public List<Freight> getFreights(@Path("linehaulid") int linehaulid);

    @GET("/0.1/contacts/{accountid}")
    public List<Contact> getContacts(@Path("accountid") int accountid);
}
