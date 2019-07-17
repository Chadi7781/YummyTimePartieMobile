package com.example.rservitawla.dao;

import com.example.rservitawla.models.ApiObject;
import com.example.rservitawla.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUser  {


    @POST("clients/auth")
    Call<ApiObject> login(@Body User user);
    @POST("clients/register")
    Call<ApiObject> register(@Body User user);

    @FormUrlEncoded
    @POST("clients/all")
    Call<User>getUserInfo(@Field("numero_telephone")String telephone);

}
