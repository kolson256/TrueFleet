package app.truefleet.com.truefleet.tests;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Activitieis.LoginActivity;
import app.truefleet.com.truefleet.R;

/**
 * Created by Chris Lacy on 1/10/2015.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    LoginActivity activity;
    EditText username;
    EditText password;
    Button login;
    Instrumentation.ActivityMonitor am;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        username = (EditText) activity.findViewById(R.id.textUsername);
        password = (EditText)activity.findViewById(R.id.textPassword);
        login = (Button)activity.findViewById(R.id.buttonLogin);
        am = getInstrumentation().addMonitor(HomeActivity.class.getName(), null, true);
    }

    public void testInitialTextFields() {

        assertNotNull(username);
        assertNotNull(password);
        assertNotNull(login);
    }
    @UiThreadTest
    public void testEmptyLogin() {

        login.performClick();
        am.waitForActivityWithTimeout(50);
        assertEquals(0, am.getHits());
    }
    @UiThreadTest
    public void testInvalidLogin() {
         username.setText("incorrect");
        password.setText("incorrect");
        login.performClick();
        am.waitForActivityWithTimeout(50);
        assertEquals(0, am.getHits());
    }

    /* Tests to ensure HomeActivity is loaded */
    @UiThreadTest
    public void testCorrectLogin() {
        username.setText("test");
        password.setText("test");
        login.performClick();
        am.waitForActivityWithTimeout(1000);
        assertEquals(1, am.getHits());
    }

}

