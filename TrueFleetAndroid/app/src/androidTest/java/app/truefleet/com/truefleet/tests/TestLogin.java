package app.truefleet.com.truefleet.tests;

import android.test.AndroidTestCase;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.LoginTask;


public class TestLogin extends AndroidTestCase {

    LoginManager loginManager;
    LoginTask loginTask;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loginManager = new LoginManager(getContext());
        loginTask = new LoginTask(null);

    }



    public void testInvalidUsername() throws InterruptedException, ExecutionException, TimeoutException {

        loginTask.execute("bad","bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());
    }

    public void testInvalaidPassword() throws InterruptedException, ExecutionException, TimeoutException {

        loginTask.execute("test", "bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());


    }
    public void testLoginSuccess() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertTrue(loginTask.getResult());
    }
    public void testRegistrationId() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);

        assertNotNull(loginManager.getRegistrationId());
    }

    public void testUserRetrieval() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);

        assertEquals("test", loginManager.getUser().getUsername());
        assertNotNull(loginManager.getUser().getApiVersion());
        assertNotNull(loginManager.getUser().getauthenticationToken());
        assertNotNull(loginManager.getUser().getTenantId());
    }
}
