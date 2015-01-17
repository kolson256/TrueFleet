package app.truefleet.com.truefleet.tests;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import app.truefleet.com.truefleet.Resources.LoginManager;
import app.truefleet.com.truefleet.Tasks.LoginTask;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 16)
public class TestLogin {
    private SharedPreferences mPrefs;
    LoginManager loginManager;
    LoginTask loginTask;

    @Before
    public void setUp() throws Exception {

        mPrefs = PreferenceManager.getDefaultSharedPreferences(Robolectric.application);
        Robolectric.getFakeHttpLayer().interceptHttpRequests(false);
        loginManager = new LoginManager(Robolectric.application);
        loginTask = new LoginTask(null);
    }

    @Test
    public void testInvalidUsername() throws InterruptedException, ExecutionException, TimeoutException {

        loginTask.execute("bad","bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());
    }

    @Test
    public void testInvalaidPassword() throws InterruptedException, ExecutionException, TimeoutException {

        loginTask.execute("test", "bad");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertFalse(loginTask.getResult());
    }

    @Test
    public void testLoginSuccess() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);
        assertTrue(loginTask.getResult());
    }

    @Test
    public void testRegistrationId() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);

        assertNotNull(loginManager.getRegistrationId());
    }

   /* @Test
    public void testUserRetrieval() throws ExecutionException, InterruptedException, TimeoutException {

        loginTask.execute("test", "test");
        loginTask.get(1000, TimeUnit.MILLISECONDS);

        //mPrefs.edit().putString("username", "test").commit();
        System.out.println(mPrefs.getString("username", "wdf"));
        assertEquals("test", loginManager.getUser().getUsername());
        assertNotNull(loginManager.getUser().getApiVersion());
        assertNotNull(loginManager.getUser().getauthenticationToken());
        assertNotNull(loginManager.getUser().getTenantId());
    } */
}