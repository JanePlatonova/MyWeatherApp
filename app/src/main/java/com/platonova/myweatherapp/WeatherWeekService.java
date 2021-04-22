package com.platonova.myweatherapp;

import com.platonova.myweatherapp.Data.Example;
import com.platonova.myweatherapp.Data.WeekWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherWeekService {
    @GET("/data/2.5/forecast")
    Call<WeekWeather> getWeatherByCityName(@Query("q") String city, @Query("appid") String appID);

}
