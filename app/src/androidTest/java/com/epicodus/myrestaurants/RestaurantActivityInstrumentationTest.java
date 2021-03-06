package com.epicodus.myrestaurants;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.epicodus.myrestaurants.ui.RestaurantsListActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by katsiarynamashokha on 10/8/17.
 */

public class RestaurantActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<RestaurantsListActivity> activityTestRule =
            new ActivityTestRule<RestaurantsListActivity>(RestaurantsListActivity.class);

    @Test
    public void listItemClickedDisplaysToastWithCorrectRestaurantName() throws Exception {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();
        String restaurantName = "Mi Mero Mole";
        onData(anything())
                .inAdapterView(withId(R.id.recycler_view))
                .atPosition(0)
                .perform(click());

        onView(withText(restaurantName)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(restaurantName)));

    }
}
