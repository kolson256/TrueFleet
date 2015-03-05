package app.truefleet.com.truefleet.tests;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import app.truefleet.com.truefleet.TrueFleetApp;
import app.truefleet.com.truefleet.tests.Mocks.MockApiModule;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
public class LoginApiTest {

    @Before
    public void setUp() throws Exception {

        TrueFleetApp.add(new MockApiModule());
        TrueFleetApp.inject(this);
    }

    @Test
    public void testLoginCorrect() throws IOException {

    }
}
