package app.truefleet.com.truefleet.tests;

import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import app.truefleet.com.truefleet.TrueFleetApp;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
public class LoginApiTest {

    @Before
    public void setUp() throws Exception {
        prepareTest();
    }

    @Test
    public void testLoginCorrect() throws IOException {

       // LoginPresenterImpl loginPresenter = new LoginPresenterImpl(mock(LoginView.class), InstrumentationRegistry.getTargetContext());
        //LoginPresenterImpl(LoginView loginView, Context context)
    }

    private void prepareTest() {
        TrueFleetApp tfApp = TrueFleetApp.get(InstrumentationRegistry.getTargetContext());
        tfApp.buildMockObjectGraphAndInject();
    }
}
