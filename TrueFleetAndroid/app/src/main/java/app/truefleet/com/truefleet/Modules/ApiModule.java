package app.truefleet.com.truefleet.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Tasks.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module(
        complete = false,
        library = true
)
public class ApiModule {
    private ApiService serviceApi;

    public static final String LOG_TAG = ApiModule.class.getSimpleName();
    private static final String genymotion = "http://10.0.3.2:8080/";
    private static final String androidEmulator = "http://10.0.2.2:8080/";

    private static final String localhost = "http://127.0.0.1:8080/"; //have to use this for now when running tests on JVM

    private static final String server = "http://140.192.30.205:8080/";
    public static final String SERVER_URL = localhost;


    @Provides @Singleton ApiService provideServerApi(Context context)
    {
        if (serviceApi == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(SERVER_URL)
                    .setConverter(new GsonConverter(gson))
                    .build();

            serviceApi = restAdapter.create(ApiService.class);
        }
        return serviceApi;

    }
}
