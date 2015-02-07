package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.GcmHelper;
import app.truefleet.com.truefleet.Resources.LoginManager;

public class HomeActivity extends Activity {

    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    LoginManager loginManager;

    GoogleCloudMessaging gcm;
    String regid;
    private final String PROJECT_NUMBER = "171359155716";
    Context context;
    Activity activity;
    HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = this;
        context = getApplicationContext();
        loginManager = new LoginManager(context);
        setTitle("Home");
        getActionBar().setIcon(R.drawable.orders);
        GcmHelper gcmHelper = new GcmHelper(getApplicationContext());
        gcmHelper.gcmSetup(this);

        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    protected void onResume() {
        super.onResume();

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
        IMTOrder order = IMTOrder.getInstance();

        if (order.getOrderType() != null) {
            Intent i = new Intent(context, OrderActivitys.class);
            startActivity(i);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
