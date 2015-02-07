package app.truefleet.com.truefleet.Resources;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import app.truefleet.com.truefleet.Tasks.RegisterGcmTask;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
public class GcmHelper {
    private final String LOG_TAG = GcmHelper.class.getSimpleName();

    Context context;
    public GcmHelper(Context context) {
        this.context = context;
    }
    public synchronized void gcmSetup(Activity activity) {
        LoginManager loginManager = new LoginManager(context);
        Log.i(LOG_TAG, "Setting up GCM");

        if (checkPlayServices()) {

            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);

            String regid = loginManager.getRegistrationId();

            Log.i(LOG_TAG, "Current registration ID on device: " + regid);

            if (regid.isEmpty()|| regid == null) {
                RegisterGcmTask gcmTask = new RegisterGcmTask(activity);
                gcmTask.execute();
            }
        }
        else {
            Log.i(LOG_TAG, "No valid Google Play Services APK found.");
        }


        if (loginManager.checkLogin())
            activity.finish();
    }
    public  boolean checkPlayServices() {
        GooglePlayServicesCheck playServicesCheck = new GooglePlayServicesCheck(context);
        GoogleCloudMessaging gcm =  GoogleCloudMessaging.getInstance(context);


        if (!playServicesCheck.checkGooglePlayServices()) {
            // Dialog dialog = GooglePlayServicesUtil.getErrorDialog(playServicesCheck.getResultCode(), this, playServicesCheck.getRQS_GooglePlayServices());
            Log.e(LOG_TAG, "Google Play Services not installed");
            // if (dialog != null)
            System.out.println("show dialog");//todo: redirect to login and show dialog?
            //  dialog.show();

            //   else //this should not happen
            //     System.out.println("show dialog");//todo: redirect to login and show dialog?
            //  showOkDialogWithText(context, "Please make sure that you have Google Play Store installed and that you are connected to the internet");
            return false;
        }
        Log.i(LOG_TAG, "Google Play Services installed");
        return true;
    }

}
