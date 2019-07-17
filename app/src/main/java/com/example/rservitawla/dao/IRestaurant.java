package com.example.rservitawla.dao;

import com.example.rservitawla.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IRestaurant {

    @GET("restaurants/all")
    Call<List<Restaurant>>getRestaurantDataService();


}
