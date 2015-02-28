package app.truefleet.com.truefleet.Resources;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import javax.inject.Inject;

import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.Requests.GcmRegisterRequest;
import app.truefleet.com.truefleet.Tasks.RestCallback;
import app.truefleet.com.truefleet.Tasks.RestError;
import app.truefleet.com.truefleet.TrueFleetApp;
import retrofit.client.Response;


public class LoginManager {
    @Inject ApiService apiService;
    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;
    private final String LOG_TAG = LoginManager.class.getSimpleName();

    int MODE = 0;
    private static final String NAME = "trufleet";
    private static final String LOGGED_IN = "IsUserLoggedIn";
    public static final String KEY_USER = "username";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_TENANTID = "tenantId";
    public static final String KEY_APIVERSION = "apiVersion";
    public static final String KEY_LAST_USER = "lastUser"; // so we know who the last user was even if they log out to send notifications correctly
    public static final String KEY_REGISTRATION_ID = "registrationId";
    private static final String KEY_APP_VERSION = "appVersion";
    private static final String KEY_TENANT_ID = "tenantId";


    public LoginManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(NAME, MODE);
        editor = sharedPreferences.edit();
        ((TrueFleetApp) context.getApplicationContext()).inject(this);
    }

    public void createLoginSession(User user) {
        editor.putBoolean(LOGGED_IN, true);
        editor.putString(KEY_USER, user.getUsername());
        editor.putString(KEY_TOKEN, user.getauthenticationToken());
        editor.putString(KEY_TENANTID, user.getTenantId());
        editor.putString(KEY_APIVERSION, user.getApiVersion());
        editor.putString(KEY_LAST_USER,  user.getUsername());
        editor.putString(KEY_TENANT_ID, user.getTenantId());
        editor.commit();
        //check if registration ID exists
        String regid = getRegistrationId();
        if (regid.isEmpty()|| regid == null) {
            Log.e(LOG_TAG, "App not yet registered with GCM upon logging in");
        }
        else {

            Log.i(LOG_TAG, "registering to server user: " + user.getUsername());

           apiService.registerGcmWithServer(user.getauthenticationToken(), user.getTenantId(),
                    new GcmRegisterRequest(user.getUsername(), regid), new RestCallback<JSONObject>() {
                        @Override
                        public void success(JSONObject jsonObject, Response response) {
                            Log.i(LOG_TAG, "Success response from server on GCM Registration");
                        }

                        @Override
                        public void failure(RestError error) {
                            System.out.println(error.getStrMessage());
                            Log.i(LOG_TAG, "Error response from server on GCM Registration: " + error.getStrMessage());
                        }
                    });

            //RegisterServerTask registerServerTask = new RegisterServerTask(context);
            //registerServerTask.execute();
        }
        //if not obtain it
        Log.i(LOG_TAG, "Stored Login information in preferences");
    }

    public boolean isUserLoggedIn(){

        return sharedPreferences.getBoolean(LOGGED_IN, false);
    }

    //Checks if logged in -> starts LoginActivity
    public boolean checkLogin() {


        if (!this.isUserLoggedIn()){
            startLoginActivity();
            return true;
        }
        return false;
    }
    public User getUser() {

        String username = sharedPreferences.getString(KEY_USER,null);
        String token = sharedPreferences.getString(KEY_TOKEN, null);
        String apiVersioin = sharedPreferences.getString(KEY_APIVERSION, null);
        String tenantId = sharedPreferences.getString(KEY_TENANTID, null);


        return new User(apiVersioin, tenantId, token, username);
    }

    public void logout() {

        //dont remove last logged in, registration id, or app version
        editor.putBoolean(LOGGED_IN, false);
        editor.remove(KEY_USER);
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_TENANTID);
        editor.remove(KEY_APIVERSION);

        editor.commit();
        startLoginActivity();

    }
    private void startLoginActivity() {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //clear activity stack
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
    public void storeRegistrationId(String regId) {

        int appVersion = getAppVersion(context);
        Log.i(LOG_TAG, "Saving regId: " + regId + " on app version " + appVersion);
        editor.putString(KEY_REGISTRATION_ID, regId );
        editor.putInt(KEY_APP_VERSION, appVersion);
        editor.commit();
    }
    public String getRegistrationId() {
        String registrationId = sharedPreferences.getString(KEY_REGISTRATION_ID, null);
        int currentVersion = getAppVersion(context);
        int registeredVersion = sharedPreferences.getInt(KEY_APP_VERSION, Integer.MIN_VALUE);
        if (registeredVersion != currentVersion) {
            Log.i(LOG_TAG, "App version changed. from: " + registeredVersion + " to " + currentVersion);

            return "";
        }

        return registrationId;
    }
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }



}
