package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.ConnectionDetector;
import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.LoginTask;

public class LoginActivity extends Activity {
    private final String LOG_TAG = LoginActivity.class.getSimpleName();

    private EditText  username=null;
    private EditText  password=null;
    private TextView attempts;
    private Button login;
    private int counter = 3;
    ConnectionDetector cd;
    boolean connectedToInternet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        getActionBar().setIcon(R.drawable.orders);

        cd = new ConnectionDetector(getApplicationContext());

        username = (EditText)findViewById(R.id.textUsername);
        password = (EditText)findViewById(R.id.textPassword);
        attempts = (TextView)findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        login = (Button)findViewById(R.id.buttonLogin);

        LoginManager loginManager = new LoginManager(getApplicationContext());

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


                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.length() < 1)
                    displayToast("Please enter a username");
                else if (password.length() < 1)
                    displayToast("Please enter a password");
                else
                {
                    LoginTask loginTask = new LoginTask(this);
                    loginTask.execute(user, pass);

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
    }
