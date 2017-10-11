package com.epicodus.myrestaurants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String LOG_TAG = RestaurantsActivity.class.getSimpleName();
    @Bind(R.id.location_zip) TextView mLocationZipTextView;
    @Bind (R.id.restaurants_list_view) ListView mListView;
    private String[] restaurants = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};

    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs",
            "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food",
            "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar",
            "Breakfast", "Mexican" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        MyRestaurantsArrayAdapter adapter = new MyRestaurantsArrayAdapter(this, R.layout.simple_expandable_list_item_1, restaurants, cuisines);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String restaurant = ((TextView)view).getText().toString();
                Log.v(LOG_TAG, restaurant);
                Toast.makeText(RestaurantsActivity.this, restaurant, Toast.LENGTH_SHORT).show();
            }
        });

        Intent restaurantIntent = getIntent();
        String zip = restaurantIntent.getStringExtra("locationZipCode");
        mLocationZipTextView.setText("Here are all the restaurants near " +  zip);



    }
}
