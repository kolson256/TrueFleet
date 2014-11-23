package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import app.truefleet.com.truefleet.Fragments.DeliveryFragment;
import app.truefleet.com.truefleet.Fragments.OrderDetailsFragment;
import app.truefleet.com.truefleet.Fragments.PickupFragment;
import app.truefleet.com.truefleet.Fragments.SidePanelFragment;
import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;

public class OrderActivitys extends Activity implements SidePanelFragment.OnColumnSelectedListener {
    private final String LOG_TAG = OrderActivitys.class.getSimpleName();
    OrderDetailsFragment orderFragment;
    DeliveryFragment deliveryFragment;
    PickupFragment pickupFragment;
    SidePanelFragment sidePanelFragment;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        orderFragment = new OrderDetailsFragment();
    deliveryFragment = new DeliveryFragment();
    pickupFragment = new PickupFragment();
    sidePanelFragment = new SidePanelFragment();

        getFragmentManager().beginTransaction().add(R.id.main_panel, orderFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.side_panel, sidePanelFragment).commit();

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
        }
     //   if (savedInstanceState == null) {
     //       getFragmentManager().beginTransaction()
      //              .add(R.id.container, new PlaceholderFragment())
      //              .commit();
       // }
    }
    private void hideSidePanel() {
        View sidePane = findViewById(R.id.side_panel);
         if (sidePane.getVisibility() == View.VISIBLE) {
             sidePane.setVisibility(View.GONE);
             }
         }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //   int id = item.getItemId();
      //  if (id == R.id.action_settings) {
       //     return true;
        //}
     //   return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onColumnSelected(int position) {
        Log.i(LOG_TAG, "Order activitys received selection: " + position);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (position ==1) {
            fragmentTransaction.replace(R.id.main_panel, pickupFragment);
        } else if(position ==2) {
            fragmentTransaction.replace(R.id.main_panel, deliveryFragment);
        } else if(position ==0) {

            fragmentTransaction.replace(R.id.main_panel, orderFragment);
        }
        else {
            return;
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void statusUpdate(View view) {
        IMTOrder order = IMTOrder.getInstance();
        String status = order.getOrderStatus();

        if (status.equalsIgnoreCase("ACCEPT")) {
            //TODO: Send accept to server
        }
    }
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            return rootView;
        }
    }

}
