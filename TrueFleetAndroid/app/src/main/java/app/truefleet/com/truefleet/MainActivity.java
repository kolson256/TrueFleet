package app.truefleet.com.truefleet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends Activity {
    private final String LOG_TAG = MainActivity.class.getSimpleName();

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
        setContentView(R.layout.activity_main);

        cd = new ConnectionDetector(getApplicationContext());

        username = (EditText)findViewById(R.id.textUsername);
        password = (EditText)findViewById(R.id.textPassword);
        attempts = (TextView)findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        login = (Button)findViewById(R.id.buttonLogin);

    }
    public class FetchLogin extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchLogin.class.getSimpleName();
        String loginStr = null;

        @Override
        //takes in login name and password params[0], params[1]
        protected String[] doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String username = params[0];
            String password = params[1];
            try {
                URL url = new URL("http://10.0.2.2:8080/hello-world?name=" + username);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {

                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                System.out.println(buffer.toString());
                if (buffer.length() == 0) {
                    return null;
                }
                loginStr = buffer.toString();
                Log.v(LOG_TAG, "Login String returned from server: " + loginStr);

                JSONObject loginJSON = new JSONObject(loginStr);

                String id = loginJSON.getString("id");
                String email = loginJSON.getString("email");

                if (email.startsWith("Hello")) {

                    login();
                }
                else
                    invalidLoginAttempt();

                return new String[0];

            //TODO: Add relevent messages for catch's
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);

                loginStr = null;
            } catch (JSONException e) {
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
    }
    public void invalidLoginAttempt() {
        Log.v(LOG_TAG, "unsuccessful login: " + username.getText().toString());
        Toast.makeText(getApplicationContext(), "Wrong Credentials",
                Toast.LENGTH_SHORT).show();
        counter--;
        attempts.setText(Integer.toString(counter));
        if (counter == 0) {
            login.setEnabled(false);
        }
    }
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    public void login() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Welcome, " + username.getText().toString() + "!");
        startActivity(intent);
    }


        public void login(View view) {
            connectedToInternet = cd.isConnectingToInternet();

            if (connectedToInternet) {
                FetchLogin loginTask = new FetchLogin();

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.length() > 1 && pass.length() > 1) {
                    loginTask.execute(user, pass);
                }
                else
                    displayToast("Username/password must be at least 2 digits long"); //TODO: real length
                //not connected to internet
            } else {
                displayToast( "Not connected to the internet. Unable to login.");
            }
        }

        public void attemptLogin() {

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
