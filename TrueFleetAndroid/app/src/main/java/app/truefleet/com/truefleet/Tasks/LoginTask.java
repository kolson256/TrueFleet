package app.truefleet.com.truefleet.Tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.R;

public class LoginTask extends AsyncTask<String, Void, String[]> {
    private WeakReference<Activity> mActivity;
    private final String LOG_TAG = LoginTask.class.getSimpleName();
    private boolean result = false;
    String loginStr = null;
    private TextView attempts;
    public LoginTask(Activity activity) {

        super();
        mActivity = new WeakReference<Activity>(activity);
    }

    @Override
    //takes in login name and password params[0], params[1]
    protected String[] doInBackground(String... params) {
        System.out.println("TEST");
        final Activity activity = mActivity.get();

        if (activity!=null)
            attempts = (TextView)activity.findViewById(R.id.textView5);

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String username = params[0];
        String password = params[1];
        JSONObject toLogin = new JSONObject();

        try {
            toLogin.put("password", password);
            toLogin.put("username", username);

            URL url = new URL("http://10.0.2.2:8080/login");
            HttpPost post = new HttpPost("http://10.0.2.2:8080/login");
            post.setHeader("Content-type", "application/json");
            post.setHeader("Accept", "application/json");

            post.setEntity(new StringEntity(toLogin.toString()));

            HttpResponse response = (new DefaultHttpClient()).execute(post);
            Log.v(LOG_TAG, "Sending 'POST' request to URL : " + url);

            String json_string = EntityUtils.toString(response.getEntity());
            if (json_string.equalsIgnoreCase("login successful"))
                login(username);
            else
                invalidLoginAttempt(username);
            return new String[0];
            //TODO: Add relevent messages for catch's
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            displayToast("Unable to connect to server");

            loginStr = null;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
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
        if (activity !=null) {
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

        if (activity!=null) {
            result = true;
            Intent intent = new Intent(activity.getApplicationContext(), HomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Welcome, " + username + "!");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.getApplicationContext().startActivity(intent);
        }
    }
    public boolean getResult() {
        return result;
    }
}
