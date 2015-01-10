package app.truefleet.com.truefleet.tests;

import android.test.ActivityInstrumentationTestCase2;

import java.util.concurrent.TimeUnit;

import app.truefleet.com.truefleet.Activitieis.HomeActivity;
import app.truefleet.com.truefleet.Tasks.LoginTask;

/**
 * Created by Chris Lacy on 1/10/2015.
 */
public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity>  {

    public HomeActivityTest() {
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertTrue(loginTask.getResult());

    }
}
