package app.truefleet.com.truefleet.Tasks;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Chris Lacy on 11/15/2014.
 */
public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {
    private final String LOG_TAG = GcmBroadcastReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Received gcm broadcast");
        Log.i(LOG_TAG, "Received gcm broadcast");

        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());

        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}