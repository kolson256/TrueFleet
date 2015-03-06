package app.truefleet.com.truefleet.Tasks;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Chris Lacy on 2/9/2015.
 */
public class RestClient {
    private static final String genymotion = "http://10.0.3.2:8080/";
    private static final String androidEmulator = "http://10.0.2.2:8080/";

    private static final String localhost = "http://127.0.0.1:8080/"; //have to use this for now when running tests on JVM

    private static final String server = "http://140.192.30.205:8080/";

    private static final String BASE_URL = genymotion;
    private ApiService apiService;

    public RestClient()
    {
        //Interceptor for stetho
        OkHttpClient client = new OkHttpClient();

        client.networkInterceptors().add(new StethoInterceptor());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(client))
                .build();

        apiService = restAdapter.create(ApiService.class);


    }


    public ApiService getApiService()
    {
        return apiService;
    }


}
