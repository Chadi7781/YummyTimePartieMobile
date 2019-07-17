package com.example.rservitawla.dao;

import com.example.rservitawla.models.Banner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IBanner {

    @GET("banners/all")
     Call<List<Banner>> getBanners();
}
