package com.epicodus.myrestaurants;

import android.os.Build;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by katsiarynamashokha on 10/7/17.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class RestaurantActivityTest {
    private RestaurantsActivity activity;
    private ListView mRestaurantListView;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(RestaurantsActivity.class);
        mRestaurantListView = (ListView) activity.findViewById(R.id.restaurants_list_view);
    }

    @Test
    public void restaurantListViewPopulates() throws Exception {
        assertNotNull(mRestaurantListView.getAdapter());
        assertEquals(mRestaurantListView.getAdapter().getCount(), 15);
    }
}
