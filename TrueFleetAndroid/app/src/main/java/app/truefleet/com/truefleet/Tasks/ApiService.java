package app.truefleet.com.truefleet.Tasks;

import org.json.JSONObject;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public interface ApiService {
    @Headers({
            "Content-type: application/json",
            "Accept: application/json"
    })
    @GET("/Login")
    public void login(@Body String username, @Body String password,
                            RestCallback<JSONObject> cb);
}
