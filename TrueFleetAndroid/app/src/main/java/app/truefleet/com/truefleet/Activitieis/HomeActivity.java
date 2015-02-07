package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.GooglePlayServicesCheck;
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
        gcmSetup();

        if (savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }

    private synchronized void gcmSetup() {
        LoginManager loginManager = new LoginManager(context);

        if (checkPlayServices()) {

            gcm = GoogleCloudMessaging.getInstance(getApplicationContext());

            regid = loginManager.getRegistrationId();

            Log.i(LOG_TAG, "Current registration ID on device: " + regid);

            if (regid.isEmpty()|| regid == null) {
                registerInBackground();
            }
        }
        else {
            Log.i(LOG_TAG, "No valid Google Play Services APK found.");
        }


        if (loginManager.checkLogin())
            finish();
    }
    private void registerInBackground() {

        Log.i(LOG_TAG, "Registering registration ID");
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg="";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                  //  gcm.unregister();
                    regid = gcm.register(PROJECT_NUMBER);

                    msg = "Device registered, registration ID=" + regid;
                    Log.i(LOG_TAG, msg);

                    if (loginManager == null)
                        loginManager = new LoginManager(context);
                    loginManager.storeRegistrationId(regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return msg;

            }

            @Override
            protected void onPostExecute(String msg) {

                Toast.makeText(activity, msg,
                        Toast.LENGTH_SHORT).show();
            }
        }.execute(null, null, null);

    }

    protected void onResume() {
        super.onResume();

    }
    private boolean checkPlayServices() {
       GooglePlayServicesCheck playServicesCheck = new GooglePlayServicesCheck(getApplicationContext());
       GoogleCloudMessaging gcm =  GoogleCloudMessaging.getInstance(context);


        if (!playServicesCheck.checkGooglePlayServices()) {
           // Dialog dialog = GooglePlayServicesUtil.getErrorDialog(playServicesCheck.getResultCode(), this, playServicesCheck.getRQS_GooglePlayServices());
              System.out.println("google play services not installed");
           // if (dialog != null)
                System.out.println("show dialog");//todo: redirect to login and show dialog?
              //  dialog.show();

         //   else //this should not happen
           //     System.out.println("show dialog");//todo: redirect to login and show dialog?
              //  showOkDialogWithText(context, "Please make sure that you have Google Play Store installed and that you are connected to the internet");
            return false;
        }
        return true;
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
