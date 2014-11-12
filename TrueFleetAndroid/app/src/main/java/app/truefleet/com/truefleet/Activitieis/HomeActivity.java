package app.truefleet.com.truefleet.Activitieis;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;

import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.Resources.GooglePlayServicesCheck;
import app.truefleet.com.truefleet.Resources.LoginManager;

public class HomeActivity extends Activity {
    private final String LOG_TAG = HomeActivity.class.getSimpleName();
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        GooglePlayServicesCheck playServicesCheck = new GooglePlayServicesCheck(getApplicationContext());


        if (!playServicesCheck.checkGooglePlayServices()) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(playServicesCheck.getResultCode(), this, playServicesCheck.getRQS_GooglePlayServices());

            if (dialog != null)
                dialog.show();
            else //this should not happen
                showOkDialogWithText(getApplicationContext(), "Please make sure that you have Google Play Store installed and that you are connected to the internet");
        }

        LoginManager loginManager = new LoginManager(getApplicationContext());

        if (loginManager.checkLogin())
            finish();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public static void showOkDialogWithText(Context context, String messageText)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageText);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void logout(View view) {
        loginManager = new LoginManager(getApplicationContext());
         loginManager.logout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
            LoginManager loginManager = new LoginManager(getActivity().getApplicationContext());
            Intent intent = getActivity().getIntent();

            if (loginManager.checkLogin())
                getActivity().finish();

            User user  = loginManager.getUser();
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            TextView tvWelcome = (TextView) rootView.findViewById(R.id.welcome_text);
            tvWelcome.setText("Welcome, " + user.getUsername() + "!");

    /*
            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                String welcome = intent.getStringExtra(Intent.EXTRA_TEXT);
                System.out.println(welcome);
                TextView tvWelcome = (TextView) rootView.findViewById(R.id.welcome_text);
                tvWelcome.setText(welcome);
            }*/

            return rootView;
        }
    }
}
