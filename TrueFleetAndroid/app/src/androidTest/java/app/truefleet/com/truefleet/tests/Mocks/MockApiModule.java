package app.truefleet.com.truefleet.tests.Mocks;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.RestClient;
import app.truefleet.com.truefleet.tests.LoginApiTest;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
@Module(
        injects = LoginApiTest.class,
        complete = false,
        library = true
)
public class MockApiModule {
    @Provides
    @Named("mock")@Singleton
    ApiService provideServerApi(Context context)
    {
        return new RestClient().getApiService();
    }
}
