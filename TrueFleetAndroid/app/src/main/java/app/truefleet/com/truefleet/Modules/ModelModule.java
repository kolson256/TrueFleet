package app.truefleet.com.truefleet.modules;

import android.content.Context;

import app.truefleet.com.truefleet.Models.OrderOverviewManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Chris Lacy on 3/2/2015.
 */
@Module(
        complete = false,
        library = true
)
public class ModelModule {
    @Provides
    public OrderOverviewManager provideOrderOverViewManager(Context context) {
        return new OrderOverviewManager(context);
    }
}
