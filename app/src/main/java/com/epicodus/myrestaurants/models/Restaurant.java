package com.epicodus.myrestaurants.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by katsiarynamashokha on 10/15/17.
 */
@Parcel
public class Restaurant{
    public String name;
    public String phone;
    public String website;
    public double rating;
    public String imageUrl;
    public ArrayList<String> address = new ArrayList<>();
    public double latitude;
    public double longitude;
    public ArrayList<String> categories = new ArrayList<>();

    // Empty constructor needed by Parcel library
    public Restaurant() {
    }

    public Restaurant(String name, String phone, String website, double rating,
                      String imageUrl, ArrayList<String> address, double latitude,
                      double longitude, ArrayList<String> categories) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
        //this.imageUrl = getLargeImageUrl(imageUrl);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getLargeImageUrl(String imageUrl) {
        return imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
    }

}
