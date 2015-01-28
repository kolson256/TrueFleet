package app.truefleet.com.truefleet.tests;

import android.test.AndroidTestCase;

import app.truefleet.com.truefleet.Models.User;
import app.truefleet.com.truefleet.Resources.LoginManager;

/**
 * Created by Chris Lacy on 1/10/2015.
 */
public class LoginManagerTest extends AndroidTestCase {
    LoginManager loginManager;
    User user;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loginManager = new LoginManager(getContext());
        user = new User("1", "1", "1", "test");

        loginManager.createLoginSession(user);
    }

    public void testIsUserLoggedIn() {
        assertTrue(loginManager.isUserLoggedIn());
    }

    public void testUserRetrieval() {
        User u = loginManager.getUser();

        assertEquals(user.getApiVersion(), u.getApiVersion());
        assertEquals(user.getUsername(), u.getUsername());
        assertEquals(user.getauthenticationToken(), u.getauthenticationToken());
        assertEquals(user.getTenantId(), u.getTenantId());
    }

    public void testLogout() {
        loginManager.logout();

        User u = loginManager.getUser();

        assertNull(user.getApiVersion(), u.getApiVersion());
        assertNull(user.getUsername(), u.getUsername());
        assertNull(user.getauthenticationToken(), u.getauthenticationToken());
        assertNull(user.getTenantId(), u.getTenantId());

        assertFalse(loginManager.isUserLoggedIn());
    }
}
