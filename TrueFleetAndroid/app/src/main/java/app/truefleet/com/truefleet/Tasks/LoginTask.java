package app.truefleet.com.truefleet.Tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.LoginManager;

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

            WebServiceHelper wsResult = WebService.invokeWSPost("Login", toLogin.toString());


            if (wsResult.getConnectionSuccess()) {

                if (!wsResult.getResponseSuccess()) {
                    //TODO: Handle error message returned by webservice
                }
                else {
                    String json_string = wsResult.getBody();
                    JSONObject userObj = new JSONObject(json_string);


                    if (!userObj.has("errorMessage")) {
                        userObj.put("username", username);
                        System.out.println(userObj.toString());
                        User user = new ObjectMapper().readValue(userObj.toString(), new TypeReference<User>(){});
                        login(user);

                    }
                    else
                        invalidLoginAttempt(username);
                }
            } else {

                displayToast("Unable to connect to server");
            }

            //TODO: Add relevent messages for catch's

        } catch (JSONException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[0];
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

    public void login(User user) {

        final Activity activity = mActivity.get();
        result = true;

        if (activity != null) {

            LoginManager manager = new LoginManager(activity.getApplicationContext());
            manager.createLoginSession(user);


            Intent intent = new Intent(activity.getApplicationContext(), HomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Welcome, " + user.getUsername() + "!");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.getApplicationContext().startActivity(intent);
            activity.finish();



        }
    }

    public boolean getResult() {
        return result;
    }
}
