package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private CountingIdlingResource mainActivityIdlingResource;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mainActivityIdlingResource = mActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mainActivityIdlingResource);
    }

    @After
    public void unRegisterIdlingResource() {
        if (mainActivityIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mainActivityIdlingResource);
        }
    }

    @Test
    public void testJokeActivityTextView_hasJokeText() {
        onView(withId(R.id.tell_joke_button)).perform(click());
        ViewInteraction jokeTextView = onView(withResourceName(Matchers.equalTo("joke_text_view")));
        jokeTextView.check(ViewAssertions.matches(withText(Matchers.not(Matchers.isEmptyOrNullString()))));
        jokeTextView.check(ViewAssertions.matches(withText("What did the router tell the doctor? \n- It hurts when IP!")));
    }
}
