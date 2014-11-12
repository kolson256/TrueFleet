package app.truefleet.com.truefleet.Resources;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import app.truefleet.com.truefleet.Activitieis.LoginActivity;
import app.truefleet.com.truefleet.Models.User;


public class LoginManager {
    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;

    int MODE = 0;
    private static final String NAME = "trufleet";
    private static final String LOGGED_IN = "IsUserLoggedIn";
    public static final String KEY_USER = "username";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_TENANTID = "tenantId";
    public static final String KEY_APIVERSION = "apiVersion";

    public LoginManager(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(NAME, MODE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(User user) {
        editor.putBoolean(LOGGED_IN, true);
        editor.putString(KEY_USER, user.getUsername());
        editor.putString(KEY_TOKEN, user.getauthenticationToken());
        editor.putString(KEY_TENANTID, user.getTenantId());
        editor.putString(KEY_APIVERSION, user.getApiVersion());

        editor.commit();

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
        editor.clear();
        editor.commit();
        startLoginActivity();

    }
    private void startLoginActivity() {
        Intent i = new Intent(context, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //clear activity stack
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }


}
