package app.truefleet.com.truefleet.Tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;
import java.lang.ref.WeakReference;

import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 2/4/2015.
 */
public class RegisterGcmTask extends AsyncTask<String, Void, String[]> {
    LoginManager loginManager;

    private final String PROJECT_NUMBER = "171359155716";
    private WeakReference<Activity> mActivity;
    private final String LOG_TAG = RegisterGcmTask.class.getSimpleName();
    private boolean result = false;


    public RegisterGcmTask(Activity activity) {

        super();
        mActivity = new WeakReference<Activity>(activity);
        loginManager = new LoginManager(activity.getApplicationContext());

    }
    @Override

    protected String[] doInBackground(String... params) {
        Log.i(LOG_TAG, "Registering GCM");
        final Activity activity = mActivity.get();
        Context context = activity.getApplicationContext();
        GoogleCloudMessaging gcm;
        String msg="";
        String regid;
        try {

        gcm = GoogleCloudMessaging.getInstance(context);

            //  gcm.unregister();
            regid = gcm.register(PROJECT_NUMBER);

            msg = "Device registered, registration ID=" + regid;
            Log.i(LOG_TAG, msg);

            if (loginManager == null)
                loginManager = new LoginManager(context);
            loginManager.storeRegistrationId(regid);
        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
            displayToast("Error registering device with Google, check internet connection");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{msg};

    }
    public void displayToast(String message) {
        final String msg = message;
        final Activity activity = mActivity.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, msg,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
