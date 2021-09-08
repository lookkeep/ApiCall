package com.firstapp.myapplication.api;

import com.firstapp.myapplication.api.request.MyApiRequest;
import com.firstapp.myapplication.api.response.Response1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCallsMethod {

    @POST("User/Authenticate")
    Call<Response1> login(@Body MyApiRequest r1);

}
