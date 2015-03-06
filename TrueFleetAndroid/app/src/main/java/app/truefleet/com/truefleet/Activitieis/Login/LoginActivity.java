package app.truefleet.com.truefleet.Activitieis.Login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import app.truefleet.com.truefleet.Activitieis.BaseActivity;
import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity implements LoginView {

    private LoginPresenter presenter;
    private final String LOG_TAG = LoginActivity.class.getSimpleName();

    @InjectView(R.id.textUsername) EditText username;
    @InjectView(R.id.textPassword) EditText password;
    @InjectView(R.id.textView5) TextView attempts;
    @InjectView(R.id.buttonLogin) com.gc.materialdesign.views.ButtonRectangle login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenterImpl(this, getApplicationContext());

        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        setTitle("Login");
    }

    public void login(View view) {

        final String strUser = username.getText().toString();
        final String pass = password.getText().toString();

        presenter.validateCredentials(strUser, pass);
        }
    @Override
    public void setLoginError() {
        Log.v(LOG_TAG, "unsuccessful login: " + username);
        displayToast("Wrong Credentials");
    }
    @Override
    public void setCommunicationError() {
        displayToast("Error communicating with server, are you connected to the internet?");
    }

    @Override
    public void usernameError() {
        displayToast("Please enter a username");
    }

    @Override
    public void passwordError() {
        displayToast("Please enter a password");
    }

    @Override
    public void notConnectedToOInternet() {
        displayToast( "Not connected to the internet. Unable to login.");
    }

    @Override
    public void navigateToHome() {

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
        finish();
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
