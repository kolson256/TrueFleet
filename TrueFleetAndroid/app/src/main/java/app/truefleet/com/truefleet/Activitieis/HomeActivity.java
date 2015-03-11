package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Events.OrderSearchEvent;
import app.truefleet.com.truefleet.Activitieis.Events.SearchClosedEvent;
import app.truefleet.com.truefleet.Fragments.HomeFragment;
import app.truefleet.com.truefleet.Models.ActiveOrderManager;
import app.truefleet.com.truefleet.Models.Order;
import app.truefleet.com.truefleet.Models.OrderOverviewManager;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.GcmHelper;
import app.truefleet.com.truefleet.Resources.LoginManager;

public class HomeActivity extends BaseActivity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();

    private ActiveOrderManager activeOrderManager;
    @Inject LoginManager loginManager;
    @Inject
    GcmHelper gcmHelper;

    @Inject
    Bus bus;

    @Inject
    OrderOverviewManager orderOverviewManager;

    Context context;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        context = getApplicationContext();
        setTitle("Home");
        activeOrderManager = activeOrderManager.getInstance();
        gcmHelper.gcmSetup(this);



        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    public static void showOkDialogWithText(Context context, String messageText)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageText);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void logout(View view) {
        loginManager = new LoginManager(context);
         loginManager.logout();
    }
    public void orders(View view) {
        showOrders();
    }
    public void showOrders() {
        Order order = activeOrderManager.getOrder();

        if (order != null) {
            Intent i = new Intent(context, OrderActivitys.class);
            startActivity(i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                //callSearch(newText);
//              }
                return true;
            }

            public void callSearch(String query) {
                bus.post(new OrderSearchEvent(query));
            }

        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bus.post(new SearchClosedEvent());
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}