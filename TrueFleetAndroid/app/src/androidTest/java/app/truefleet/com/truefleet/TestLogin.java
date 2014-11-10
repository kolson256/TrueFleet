package app.truefleet.com.truefleet;

import android.test.AndroidTestCase;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import app.truefleet.com.truefleet.Tasks.LoginTask;


public class TestLogin extends AndroidTestCase {
    public void testLoginSuccess() throws ExecutionException, InterruptedException, TimeoutException {

        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertTrue(loginTask.getResult());
    }

    public void testInvalidUsername() throws InterruptedException, ExecutionException, TimeoutException {

        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("bad","bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());
    }

    public void testInvalaidPassword() throws InterruptedException, ExecutionException, TimeoutException {

        LoginTask loginTask = new LoginTask(null);
        loginTask.execute("test", "bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());
    }
}
