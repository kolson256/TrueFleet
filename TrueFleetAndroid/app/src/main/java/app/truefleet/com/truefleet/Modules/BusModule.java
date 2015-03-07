package app.truefleet.com.truefleet.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 3/6/2015.
 */
@Module(
        complete = false,
        library = true,
        overrides = true
)
public class BusModule {
    @Provides
    @Singleton
    Bus providesBus() {
        return new Bus();
    }
}
