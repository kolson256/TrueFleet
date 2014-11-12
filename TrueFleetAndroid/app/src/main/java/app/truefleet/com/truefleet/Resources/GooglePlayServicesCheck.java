package app.truefleet.com.truefleet.Resources;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;

import static com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable;

/**
 * Created by Chris Lacy on 11/12/2014.
 */
public class GooglePlayServicesCheck {
    private final String LOG_TAG = GooglePlayServicesCheck.class.getSimpleName();
    Context context;
    int resultCode;
    int RQS_GooglePlayServices;

    public GooglePlayServicesCheck(Context context) {

        this.context = context;
        this.RQS_GooglePlayServices = 0;
    }

    public boolean checkGooglePlayServices() {

        resultCode = isGooglePlayServicesAvailable(context);

        if (resultCode == ConnectionResult.SUCCESS){
            Log.v(LOG_TAG, "Google play services installed");
            return true;
        }
        else{
            Log.v(LOG_TAG, "Google play services not installed");
            return false;
        }
    }
    public int getResultCode() {
        return resultCode;
    }
    public int getRQS_GooglePlayServices() {
        return RQS_GooglePlayServices;
    }

}
