package app.truefleet.com.truefleet.tests;

import android.support.test.espresso.action.CloseKeyboardAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.truefleet.com.truefleet.Activitieis.Login.LoginActivity;
import app.truefleet.com.truefleet.R;
import app.truefleet.com.truefleet.util.ActivityRule;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Chris Lacy on 3/4/2015.
 */
@RunWith(AndroidJUnit4.class)
public class LoginTestEspresso {
    @Rule
    public final ActivityRule<LoginActivity> home = new ActivityRule<>(LoginActivity.class);

    @Test
    public void testVisibility() {

        onView(withId(R.id.buttonLogin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textUsername)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textPassword)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
    @Test
    public void testInput() {
        onView(withId(R.id.textUsername)).perform(typeText("hello"));
        onView(withId(R.id.textUsername)).check(matches(withText("hello")));

        onView(withId(R.id.textPassword)).perform(typeText("there"));
        onView(withId(R.id.textPassword)).check(matches(withText("there")));
    }
    @Test
    public void testCorrectLogin() {
        String username = "test";
        onView(withId(R.id.textUsername)).perform(typeText(username));
        onView(withId(R.id.textPassword)).perform(typeText(username));
        onView(isRoot()).perform(new CloseKeyboardAction());
        onView(withId(R.id.buttonLogin)).perform(click());

       // onView(withId(R.id.welcome_text)).check(ViewAssertions.matches(ViewMatchers.withText("Hello, " + username + "!")));
       // onView(withId(R.id.buttonLogin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

}
