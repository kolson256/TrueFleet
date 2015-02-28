package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import app.truefleet.com.truefleet.TrueFleetApp;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public abstract class BaseActivity extends Activity {
   // @Inject AppContainer appContainer;
    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TrueFleetApp app = TrueFleetApp.get(this);

        app.getObjectGraph().inject(this);
        //app.inject(this);

       // container = appContainer.get(this);
    }
}
