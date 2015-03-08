package app.truefleet.com.truefleet.Activitieis;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.TrueFleetApp;

/**
 * Created by Chris Lacy on 2/27/2015.
 */
public abstract class BaseActivity extends ActionBarActivity {
   // @Inject AppContainer appContainer;
    private ViewGroup container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TrueFleetApp) getApplication()).inject(this);
        getSupportActionBar().setIcon(R.drawable.orders);
     // getSupportActionBar().setCustomView(R.layout.action_bar);
      //  centerActionBarTitle();
    }

    private void centerActionBarTitle()
    {

        int titleId = 0;
        int subtitleId = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            titleId = getResources().getIdentifier("action_bar_title", "id", "android");
            subtitleId = getResources().getIdentifier("action_bar_subtitle", "id", "android");
        }
        else
        {
            // This is the id is from your app's generated R class when ActionBarActivity is used
            // for SupportActionBar
            titleId = R.id.action_bar_subtitle;
        }

        // Final check for non-zero invalid id
        if (titleId > 0)
        {
            TextView titleTextView = (TextView) findViewById(titleId);



            DisplayMetrics metrics = getResources().getDisplayMetrics();

            // Fetch layout parameters of titleTextView (LinearLayout.LayoutParams : Info from HierarchyViewer)
            LinearLayout.LayoutParams txvPars = (LinearLayout.LayoutParams) titleTextView.getLayoutParams();
            txvPars.gravity = Gravity.CENTER_HORIZONTAL;
            txvPars.width = metrics.widthPixels;
            titleTextView.setLayoutParams(txvPars);

            titleTextView.setGravity(Gravity.CENTER);

            TextView subtitleTextView = (TextView) findViewById(subtitleId);

            subtitleTextView.setHeight(titleTextView.getHeight());

        }
    }
}
