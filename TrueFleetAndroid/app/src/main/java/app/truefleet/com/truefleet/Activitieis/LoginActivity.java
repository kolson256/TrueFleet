package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.ConnectionDetector;
import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.ApiService;
import app.truefleet.com.truefleet.Tasks.Requests.LoginRequest;
import app.truefleet.com.truefleet.Tasks.RestCallback;
import app.truefleet.com.truefleet.Tasks.RestClient;
import app.truefleet.com.truefleet.Tasks.RestError;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.client.Response;

public class LoginActivity extends Activity {
    private final String LOG_TAG = LoginActivity.class.getSimpleName();
    LoginManager loginManager;

    @InjectView(R.id.textUsername) EditText username;
    @InjectView(R.id.textPassword) EditText password;
    @InjectView(R.id.textView5) TextView attempts;
    @InjectView(R.id.buttonLogin) Button login;

    ConnectionDetector cd;
    boolean connectedToInternet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        setTitle("Login");
        getActionBar().setIcon(R.drawable.orders);

        cd = new ConnectionDetector(getApplicationContext());

        loginManager = new LoginManager(getApplicationContext());

       // if (loginManager.checkLogin())
        //    finish();
        
    }


    public void displayToast(String message) {
        final String msg = message;
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

        public void login(View view) {
            connectedToInternet = cd.isConnectingToInternet();

            if (connectedToInternet) {


                final String strUser = username.getText().toString();
                final String pass = password.getText().toString();

                if (strUser.length() < 1)
                    displayToast("Please enter a username");
                else if (password.length() < 1)
                    displayToast("Please enter a password");
                else
                {

                    RestClient rc = new RestClient();
                   ApiService as = rc.getApiService();

                    as.login(new LoginRequest(strUser, pass), new RestCallback<User>() {
                        @Override
                        public void success(User user, Response response) {

                            if (user.getApiVersion() != null) {
                                user.setUsername(strUser);
                                loginManager.createLoginSession(user);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Welcome, " + user.getUsername() + "!");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);
                                finish();
                            }
                            else
                            {
                                invalidLoginAttempt(strUser);
                            }
                        }
                        @Override
                        public void failure(RestError error) {
                            displayToast("Error communicating with server, are you connected to the internet?");
                            Log.i(LOG_TAG, "Error: " + error.getStrMessage());
                        }
                    });
                   // LoginTask loginTask = new LoginTask(this);
                  //  loginTask.execute(user, pass);

                }
            } else {
                displayToast( "Not connected to the internet. Unable to login.");
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        /**
         * A placeholder fragment containing a simple view.
         */
        public static class PlaceholderFragment extends Fragment {

            public PlaceholderFragment() {
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);

                return rootView;
            }
        }
    public void invalidLoginAttempt(String username) {

        Log.v(LOG_TAG, "unsuccessful login: " + username);
        displayToast("Wrong Credentials");
    }
    }
