package app.truefleet.com.truefleet.Tasks;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class WebService {
    private static final String LOG_TAG = WebService.class.getSimpleName();
    private static final String genymotion = "http://10.0.3.2:8080/";
    private static final String androidEmulator = "http://10.0.2.2:8080/";
    private static final String SERVER = "http://140.192.30.205:8080/";

    private static final String URL = SERVER;

    public static WebServiceHelper invokeWSPost(String serviceName, String body) throws MalformedURLException, UnsupportedEncodingException {

        HttpPost post = new HttpPost(URL + serviceName);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");

        return invoke(serviceName, body, post);
    }

    public static WebServiceHelper invokeWSAuthorizationPost(String serviceName, String body, String token) throws MalformedURLException, UnsupportedEncodingException {

        HttpPost post = new HttpPost(URL + serviceName);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("authToken", token);

        return invoke(serviceName, body, post);
    }

    private static WebServiceHelper invoke(String serviceName, String body, HttpPost post) throws MalformedURLException, UnsupportedEncodingException {

        post.setEntity(new StringEntity(body));

        try {
            String error = "";
            HttpResponse response = (new DefaultHttpClient()).execute(post);
            Log.v(LOG_TAG, "Sending 'POST' request to URL : " + URL + serviceName);
            String json_string = EntityUtils.toString(response.getEntity());

            JSONObject json = new JSONObject(json_string);
            Log.v(LOG_TAG,"Received back: " + json_string);

            if (json.has("errorMessage"))
                return new WebServiceHelper(true, json.getString("errorMessage"), false);

            return new WebServiceHelper(true, json_string, true);

        } catch (IOException e) {

            Log.e(LOG_TAG, "Error executing response for url: " + URL + serviceName);
            e.printStackTrace();
            return new WebServiceHelper(false, "Error executing response", false);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Received non json response from server");


            e.printStackTrace();
            return new WebServiceHelper(false, "Received invalid response back from server", false);
        }

    }
}