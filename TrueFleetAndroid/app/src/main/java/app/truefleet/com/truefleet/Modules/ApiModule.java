package app.truefleet.com.truefleet.modules;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.RestClient;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module(
        complete = false,
        library = true
)
public class ApiModule {

    @Provides
    @Named("real")
    @Singleton
    ApiService provideServerApi(Context context) {
        return new RestClient().getApiService();
    }

}
