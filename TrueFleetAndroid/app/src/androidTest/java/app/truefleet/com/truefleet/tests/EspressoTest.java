package app.truefleet.com.truefleet.tests;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.util.ActivityRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule public final ActivityRule<LoginActivity> home = new ActivityRule<>(LoginActivity.class);

    @Test
    public void testHello() {
        onView(withText("TruFleet")).check(ViewAssertions.matches(isDisplayed()));
    }


}
