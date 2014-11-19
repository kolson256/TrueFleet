package app.truefleet.com.truefleet.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 11/19/2014.
 */
public class RegisterServerTask extends AsyncTask<String, Void, String[]> {
    private Context context;
    private final String LOG_TAG = LoginTask.class.getSimpleName();
    private boolean result = false;
    private TextView attempts;
    LoginManager loginManager;
    private final static String ERROR_TAG = "errorMessage";
    private final static String SUCCESS_TAG = "message";
    private final static String SUCESS_VALUE = "success";

    public RegisterServerTask(Context ctx) {

        super();
        context = ctx;
        loginManager = new LoginManager(ctx);
    }

    @Override
    //takes in login name and password params[0], params[1]
    protected String[] doInBackground(String... params) {

        String username = loginManager.getUser().getUsername();
        String authToken = loginManager.getUser().getauthenticationToken();
        String registrationId = loginManager.getRegistrationId();

        JSONObject toRegister = new JSONObject();

        try {
            toRegister.put("registrationId", registrationId);
            toRegister.put("username", username);

            WebServiceHelper wsResult = WebService.invokeWSAuthorizationPost("GcmRegistration", toRegister.toString(), authToken);

            if (wsResult.getConnectionSuccess()) {

                if (!wsResult.getResponseSuccess()) {
                    //TODO: Handle error message returned by webservice - invalid authtoken, username, etc..

                } else {
                    String json_string = wsResult.getBody();
                    JSONObject registerObj = new JSONObject(json_string);
                    Log.i(LOG_TAG, "Result from server: " + registerObj.toString());
                    if (registerObj.has(SUCCESS_TAG)) {
                        if (registerObj.getString(SUCCESS_TAG).equalsIgnoreCase(SUCESS_VALUE)) {
                            Log.i(LOG_TAG, SUCESS_VALUE);

                        } else {
                            Log.i(LOG_TAG, "Unexpected treturn from server");
                        }
                    } else {
                        Log.e(LOG_TAG, "Unexpected return from server");
                    }
                }
            } else {

                //TODO: handle unable to connect to server
            }

            //TODO: Add relevent messages for catch's
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
