package com.platonova.myweatherapp;

import com.platonova.myweatherapp.Data.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherOneDayApiService {
    @GET("/data/2.5/weather")
    Call<Example> getWeatherByCityName(@Query("q") String city, @Query("appid") String appID);

}