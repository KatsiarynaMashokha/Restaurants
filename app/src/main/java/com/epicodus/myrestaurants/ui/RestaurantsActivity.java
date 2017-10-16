package com.epicodus.myrestaurants.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantsActivity.class.getSimpleName();
    @Bind(R.id.location_zip) TextView mLocationZipTextView;
    @Bind (R.id.restaurants_list_view) ListView mListView;
    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        Intent restaurantIntent = getIntent();
        String location = restaurantIntent.getStringExtra("locationZipCode");
        mLocationZipTextView.setText("Here are all the restaurants near " +  location);
        getRestaurants(location);
    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()) {
                        restaurants = yelpService.processResult(response);

                        RestaurantsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String[] restaurantNames = new String[restaurants.size()];
                                for(int i = 0; i < restaurantNames.length; i++) {
                                    restaurantNames[i] = restaurants.get(i).getName();
                                    Log.d(TAG, "name is " + restaurants.get(i).getName());
                                }
                                Log.d(TAG, "lenght is " + restaurantNames.length);

                                ArrayAdapter adapter = new ArrayAdapter(RestaurantsActivity.this,
                                        android.R.layout.simple_list_item_1, restaurantNames);
                                mListView.setAdapter(adapter);

                                for (Restaurant restaurant : restaurants) {
                                    Log.d(TAG, "Name: " + restaurant.getName());
                                    Log.d(TAG, "Phone: " + restaurant.getPhone());
                                    Log.d(TAG, "Website: " + restaurant.getWebsite());
                                    Log.d(TAG, "Image url: " + restaurant.getImageUrl());
                                    Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
                                    Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
                                    Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
                                }
                            }
                        });
                    }
            }
        });
    }
}
