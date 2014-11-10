package app.truefleet.com.truefleet;

import android.test.AndroidTestCase;

import app.truefleet.com.truefleet.Tasks.LoginTask;

/**
 * Created by night_000 on 11/9/2014.
 */
public class TestLogin extends AndroidTestCase {
    public void testLoginSuccess() {

        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("test", "test");
        assertTrue(loginTask.getResult());
    }
    public void testInvalidUsername() {
        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("bad","bad");
        assertFalse(loginTask.getResult());
    }
    public void testInvalaidPassword() {
        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("test", "bad");
        assertFalse(loginTask.getResult());
    }
}
