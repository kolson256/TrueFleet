package app.truefleet.com.truefleet.Activitieis;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.truefleet.com.truefleet.Fragments.ActiveLinehaulFragment;
import app.truefleet.com.truefleet.Fragments.ContainerFragment;
import app.truefleet.com.truefleet.Fragments.DeliveryFragment;
import app.truefleet.com.truefleet.Fragments.FreightFragment;
import app.truefleet.com.truefleet.Fragments.OrderDetailsFragment;
import app.truefleet.com.truefleet.Fragments.PickupFragment;
import app.truefleet.com.truefleet.Fragments.SidePanelFragment;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.R;

public class OrderActivitys extends BaseActivity implements SidePanelFragment.OnColumnSelectedListener {
    private final String LOG_TAG = OrderActivitys.class.getSimpleName();
    OrderDetailsFragment orderFragment;
    DeliveryFragment deliveryFragment;
    PickupFragment pickupFragment;
    SidePanelFragment sidePanelFragment;
    ActiveLinehaulFragment activeLinehaulFragment;
    ContainerFragment containerFragment;
    FreightFragment freightFragment;


    int id;
    private BroadcastReceiver broadcastReceiver;

    public class FragmentReceiverOrderDetails extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            boolean value = intent.getBooleanExtra("STATUS", false);
            if (value) {
                displayToast("Status sent to server successfully");
            }
            else {
                displayToast("Failed sending status to server");
            }

        }
    }
    public void displayToast(String message) {
        final String msg = message;
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        broadcastReceiver = new FragmentReceiverOrderDetails();
        registerReceiver(broadcastReceiver, new IntentFilter("orderstatus"));
        ActiveOrderManager activeOrderManager = ActiveOrderManager.getInstance();

        setTitle("Order Details");
        getSupportActionBar().setSubtitle("#" + activeOrderManager.getOrder().orderid);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderFragment = new OrderDetailsFragment();
    deliveryFragment = new DeliveryFragment();
    pickupFragment = new PickupFragment();
    sidePanelFragment = new SidePanelFragment();
        activeLinehaulFragment = new ActiveLinehaulFragment();
        containerFragment = new ContainerFragment();
        freightFragment = new FreightFragment();

        getFragmentManager().beginTransaction().add(R.id.main_panel, orderFragment).commit();
        getFragmentManager().beginTransaction().add(R.id.side_panel, sidePanelFragment).commit();

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            hideSidePanel();
        }

        //initialize w pickup fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_panel, pickupFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        Fragment toSwitch;

        switch (position) {

            case 0:
                toSwitch = pickupFragment;
                break;
            case 1:
                toSwitch = deliveryFragment;
                break;
            case 2:
                toSwitch = freightFragment;
                break;
            case 3:
                toSwitch = containerFragment;
                break;
            default:
                toSwitch = pickupFragment;
                break;

        }

        //fragmentTransaction.replace(R.id.main_panel, toSwitch);

        fragmentTransaction.replace(R.id.content_panel, toSwitch);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void statusUpdate(View view) {
;
        //TODO: HANDLE STATUS WHEN AVL IN SERVER
        Log.i(LOG_TAG, "Status button clicked");

    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_order, container, false);

            return rootView;
        }
    }
//Stop receiver from leaking
    @Override
    public void onStop()
    {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {
            Log.d(LOG_TAG, "Receiver already unregistered");
        }
        super.onStop();
    }

}
