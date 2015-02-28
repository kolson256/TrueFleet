package app.truefleet.com.truefleet;

import android.content.Context;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Activitieis.LoginActivity;
import app.truefleet.com.truefleet.modules.ApiModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module (
        includes = {
                ApiModule.class
        },
        injects = {
               LoginActivity.class
        }
)
public class TrueFleetModule {

    private final Context app;


    public TrueFleetModule(Context app) {
        this.app = app;
    }

    @Provides @Singleton
    Context provideApplicationContext() { return app; }
}
