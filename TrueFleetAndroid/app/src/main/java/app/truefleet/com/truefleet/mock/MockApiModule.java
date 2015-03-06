package app.truefleet.com.truefleet.mock;

/**
 * Created by Chris Lacy on 3/5/2015.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Tasks.ApiService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.GsonConverter;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
@Module(

        complete = false,
        library = true,
        overrides = true
)
public class MockApiModule {

    @Provides
    @Singleton
    Client provideMockClient() {
        return new MockClient();
    }
    @Provides
    @Singleton
    ApiService mockApiService(Client client) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://localhost:8080")
                .setClient(client)
                .setConverter(new GsonConverter(gson))
                .build();

        return restAdapter.create(ApiService.class);
    }
}