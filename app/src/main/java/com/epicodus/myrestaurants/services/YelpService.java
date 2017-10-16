package com.epicodus.myrestaurants.services;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.models.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by katsiarynamashokha on 10/14/17.
 */

public class YelpService {
    public static void findRestaurants(String location, Callback callback) {
        // Creates and sends request
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        // Creates new Url using the base Url
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        // Adds a location parameter
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAM, location);
        // Turns the final Url into a string
        String url = urlBuilder.build().toString();

        // Create a request object
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        // A call is a request that has been prepared for execution
        Call call = client.newCall(request);
        // Schedules to execute a request at some time in the future (asynchronous)
        call.enqueue(callback);
    }

    public ArrayList<Restaurant> processResult(Response response) {
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject yelpJsonObject = new JSONObject(jsonData);
            JSONArray businessJsonArray = yelpJsonObject.getJSONArray("businesses");
            for (int i = 0; i < businessJsonArray.length(); i++) {
                JSONObject restaurantJsonObject = businessJsonArray.getJSONObject(i);
                String name = restaurantJsonObject.getString("name");
                String phone = restaurantJsonObject.optString("display_phone", "Phone not available");
                String website = restaurantJsonObject.getString("url");
                double rating = restaurantJsonObject.getDouble("rating");
                String imageUrl = restaurantJsonObject.getString("image_url");
                double latitude = restaurantJsonObject.getJSONObject("coordinates").getDouble("latitude");
                double longitude = restaurantJsonObject.getJSONObject("coordinates").getDouble("longitude");

                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJsonArray = restaurantJsonObject.getJSONObject("location").getJSONArray("display_address");
                for (int j = 0; j < addressJsonArray.length(); j++) {
                    address.add(addressJsonArray.get(j).toString());
                }

                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoriesJsonArray = restaurantJsonObject.getJSONArray("categories");
                for (int y = 0; y < categoriesJsonArray.length(); y++) {
                    categories.add(categoriesJsonArray.getJSONObject(y).getString("title"));
                }
                Restaurant restaurant = new Restaurant(name, phone, website, rating, imageUrl,
                        address, latitude, longitude, categories);
                restaurants.add(restaurant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
