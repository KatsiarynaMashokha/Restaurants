package com.epicodus.myrestaurants;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.epicodus.myrestaurants.ui.MainActivity;
import com.epicodus.myrestaurants.ui.RestaurantsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

/**
 * Created by katsiarynamashokha on 10/7/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class MainActivityTest {
    private MainActivity activity;


    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() throws Exception {
        TextView appNameTextView = (TextView) activity.findViewById(R.id.textView);
        assertTrue("MyRestaurants".equals(appNameTextView.getText().toString()));
    }

    @Test
    public void restaurantActivityStartedOnClick() throws Exception {
        activity.findViewById(R.id.findRestaurantsButton).performClick();
        Intent expectedIntent = new Intent(activity, RestaurantsActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
