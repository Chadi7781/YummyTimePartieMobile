package com.example.rservitawla.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Abhi on 06 Sep 2017 006.
 */

public class RestaurantList {

    @SerializedName("restautant")
    @Expose
    private ArrayList<Restaurant> restaurant = null;

    public ArrayList<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(ArrayList<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }

}