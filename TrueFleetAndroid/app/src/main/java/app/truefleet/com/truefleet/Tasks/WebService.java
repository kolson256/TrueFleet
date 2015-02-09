package app.truefleet.com.truefleet.Tasks;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import app.truefleet.com.truefleet.Models.User;

public class WebService {
    private static final String LOG_TAG = WebService.class.getSimpleName();
    private static final String genymotion = "http://10.0.3.2:8080/";
    private static final String androidEmulator = "http://10.0.2.2:8080/";

    private static String localhost = "http://127.0.0.1:8080/"; //have to use this for now when running tests on JVM

    private static final String server = "http://140.192.30.205:8080/";

    private static final String URL = genymotion;

    public static WebServiceHelper invokeWSPost(String serviceName, String body) throws MalformedURLException, UnsupportedEncodingException {

        HttpPost post = new HttpPost(URL + serviceName);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");

        return invoke(serviceName, body, post);
    }
    public static WebServiceHelper invokeWSGet(String serviceName, String body, User user) throws MalformedURLException, UnsupportedEncodingException {

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(URL + serviceName);

        get.setHeader("Content-type", "application/json");
        get.setHeader("authToken", user.getauthenticationToken());
        get.setHeader("tenantId", user.getTenantId());
        HttpResponse response;
        String result = null;

        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result= EntityUtils.toString(response.getEntity());
                Log.i("LOG_TAG", "Received back from GET: " + result);
                if (checkResponse(response)) {
                    return new WebServiceHelper(true, result, true);
                }
                return new WebServiceHelper(true, result, false);

                }

            } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new WebServiceHelper(false, "", false);
    }
    public static boolean checkResponse (HttpResponse response) {
        int code = response.getStatusLine().getStatusCode();

        if (code!=200) {
            return false;
        }
        return true;
    }
    public static WebServiceHelper invokeWSAuthorizationPost(String serviceName, String body, String token, String tenantId) throws MalformedURLException, UnsupportedEncodingException {

        HttpPost post = new HttpPost(URL + serviceName);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setHeader("authToken", token);
        post.setHeader("tenantId", tenantId);

        return invoke(serviceName, body, post);
    }

    private static WebServiceHelper invoke(String serviceName, String body, HttpPost post) throws MalformedURLException, UnsupportedEncodingException {

        post.setEntity(new StringEntity(body));
        try {
            String error = "";
            HttpResponse response = (new DefaultHttpClient()).execute(post);
            Log.v(LOG_TAG, "Sending 'POST' request to URL : " + URL + serviceName);
            String json_string = EntityUtils.toString(response.getEntity());

            Log.v(LOG_TAG,"Received back: " + json_string);

            JSONObject json = new JSONObject(json_string);


            if (json.has("errorMessage"))
                return new WebServiceHelper(true, json.getString("errorMessage"), false);

            if (checkResponse(response))
             return new WebServiceHelper(true, json_string, true);
            else
                return new WebServiceHelper(true, json.getString("errorMessage"), false);

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