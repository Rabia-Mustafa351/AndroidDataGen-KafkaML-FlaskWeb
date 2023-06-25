package com.example.datacollectionapp;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface Service {


    @POST("/wr")
    void setDataModel(@Body DataModel dataModel, Callback<JsonArray> callback);

}
