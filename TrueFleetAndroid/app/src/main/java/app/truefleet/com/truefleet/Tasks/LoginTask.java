package app.truefleet.com.truefleet.Tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.R;

public class LoginTask extends AsyncTask<String, Void, String[]> {

    private WeakReference<Activity> mActivity;
    private final String LOG_TAG = LoginTask.class.getSimpleName();
    private boolean result = false;
    private TextView attempts;

    public LoginTask(Activity activity) {

        super();
        mActivity = new WeakReference<Activity>(activity);
    }

    @Override
    //takes in login name and password params[0], params[1]
    protected String[] doInBackground(String... params) {

        final Activity activity = mActivity.get();

        if (activity != null)
            attempts = (TextView) activity.findViewById(R.id.textView5);


        String username = params[0];
        String password = params[1];
        JSONObject toLogin = new JSONObject();

        try {
            toLogin.put("password", password);
            toLogin.put("username", username);

            WebServiceHelper wsResult = WebService.invokeWSPost("login", toLogin.toString());

            if (wsResult.connectionSuccess) {
                String json_string = wsResult.result;

                if (json_string.equalsIgnoreCase("login successful"))
                    login(username);
                else
                    invalidLoginAttempt(username);

            } else {

                displayToast("Unable to connect to server");
            }
            return new String[0];
            //TODO: Add relevent messages for catch's

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void invalidLoginAttempt(String username) {
        result = false;
        Log.v(LOG_TAG, "unsuccessful login: " + username);
        displayToast("Wrong Credentials");
        // runOnUiThread(new Runnable() {
        //  @Override
        //  public void run() {
        //        counter--

        //     attempts.setText(Integer.toString(counter));
        //    if (counter == 0) {
        //       login.setEnabled(false);
        //  }
        //}
        //});

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

    public void login(String username) {

        final Activity activity = mActivity.get();
        result = true;

        if (activity != null) {

            Intent intent = new Intent(activity.getApplicationContext(), HomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Welcome, " + username + "!");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.getApplicationContext().startActivity(intent);

        }
    }

    public boolean getResult() {
        return result;
    }
}
