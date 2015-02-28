package app.truefleet.com.truefleet.modules;

import javax.inject.Singleton;

import app.truefleet.com.truefleet.AppContainer;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
@Module(
        injects = {
           // BaseActivity.class
        },
        complete = false,
        library = true
)
public class UiModule {
    @Provides
    @Singleton
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }

}
