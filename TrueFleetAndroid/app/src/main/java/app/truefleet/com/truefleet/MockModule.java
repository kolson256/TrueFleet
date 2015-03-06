package app.truefleet.com.truefleet;

import android.content.Context;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.Activitieis.Login.LoginInteractorImpl;
import app.truefleet.com.truefleet.Activitieis.OrderActivitys;
import app.truefleet.com.truefleet.Fragments.HomeFragment;
import app.truefleet.com.truefleet.Models.OrderOverviewManager;
import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.OrderService;
import app.truefleet.com.truefleet.mock.MockApiModule;
import app.truefleet.com.truefleet.modules.ModelModule;
import app.truefleet.com.truefleet.modules.ResourceModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 3/5/2015.
 */
@Module(
        includes = {
                MockApiModule.class,
                ResourceModule.class,
                ModelModule.class
        },
        injects = {
                LoginActivity.class,
                HomeActivity.class,
                OrderActivitys.class,
                LoginInteractorImpl.class,
                TrueFleetApp.class,
                LoginManager.class,
                OrderOverviewManager.class,
                HomeFragment.class,
                OrderService.class
        }
)
public class MockModule {

    private final Context app;


    public MockModule(Context app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() { return app; }
}

