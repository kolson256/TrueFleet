package app.truefleet.com.truefleet;

import android.content.Context;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.Activitieis.Login.LoginInteractorImpl;
import app.truefleet.com.truefleet.Activitieis.OrderActivitys;
import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.modules.ResourceModule;
import app.truefleet.com.truefleet.modules.ApiModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module (
        includes = {
                ApiModule.class,
                ResourceModule.class
        },
        injects = {
                LoginActivity.class,
                HomeActivity.class,
                OrderActivitys.class,
                LoginInteractorImpl.class,
               TrueFleetApp.class,
                LoginManager.class
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
