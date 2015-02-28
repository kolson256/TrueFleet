package app.truefleet.com.truefleet.modules;

import android.content.Context;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.Resources.ConnectionDetector;
import app.truefleet.com.truefleet.Resources.GcmHelper;
import app.truefleet.com.truefleet.Resources.GooglePlayServicesCheck;
import app.truefleet.com.truefleet.Resources.LoginManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module(
        complete = false,
        library = true
)
public class ResourceModule {

    @Provides
    public LoginManager provideLoginManager(Context context) {
        return new LoginManager(context);
    }

    @Provides
    public ConnectionDetector provideConnectionDetector(Context context) {
        return new ConnectionDetector(context);
    }

    @Provides @Singleton
    public GcmHelper providesGcmHelper(Context context) {
        return new GcmHelper(context);
    }

    @Provides
    public GooglePlayServicesCheck provideGooglePlayServicesCheck(Context context) {
        return new GooglePlayServicesCheck(context);
    }

}
