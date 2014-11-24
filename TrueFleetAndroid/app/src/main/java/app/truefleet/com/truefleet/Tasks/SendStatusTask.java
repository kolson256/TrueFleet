package app.truefleet.com.truefleet.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import app.truefleet.com.truefleet.Models.IMTOrder;
import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 11/23/2014.
 */
public class SendStatusTask extends AsyncTask<String, Void, String[]> {
    private Context context;
    private final String LOG_TAG = SendStatusTask.class.getSimpleName();
    LoginManager loginManager;

    public SendStatusTask(Context ctx) {

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
        IMTOrder order = IMTOrder.getInstance();
        JSONObject statusObj = new JSONObject();

        try {
            statusObj.put("username", username);
            statusObj.put("internalId", order.getInternalID());
            statusObj.put("status", params[0]);
            WebServiceHelper wsResult = WebService.invokeWSAuthorizationPost("status", statusObj.toString(), authToken, loginManager.getUser().getTenantId());

            if (wsResult.getConnectionSuccess() && wsResult.getResponseSuccess()) {
                //TODO: Handle status sucessfully updated in server
                Intent intent = new Intent("orderstatus");
                intent.putExtra("STATUS", true);
                context.getApplicationContext().sendBroadcast(intent);
            }
            else {
                //TODO: Handle bad response from server
                Intent intent = new Intent("orderstatus");
                intent.putExtra("STATUS", false);
                context.getApplicationContext().sendBroadcast(intent);
            }
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