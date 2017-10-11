package com.epicodus.myrestaurants;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by katsiarynamashokha on 10/7/17.
 */

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void validateEditText() throws Exception {
        onView(withId(R.id.zipcode)).perform(typeText("Portland"))
                .check(matches(withText("Portland")));

    }

    @Test
    public void locationIsSentToRestaurantActivity() throws Exception {
        String location = "Portland";
        onView(withId(R.id.zipcode)).perform(typeText(location));
        onView(withId(R.id.findRestaurantsButton)).perform(click());
        onView(withId(R.id.location_zip)).check(matches
                (withText("Here are all the restaurants near " + location)));
    }
}
