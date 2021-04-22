package com.platonova.myweatherapp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.platonova.myweatherapp.Data.Example;
import com.platonova.myweatherapp.Data.WeatherEntity;

import java.util.concurrent.Executors;

public class Repository {
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    public Repository(Context context){
        localDataSource = new LocalDataSource(context);
        remoteDataSource = new RemoteDataSource();
    }
    public void storeWeatherforDay(Example example){
        WeatherEntity we=new WeatherEntity();
       // Log.d("storeWeather", String.valueOf(example.getMain().getTemp().toString()));
        WeatherEntity weatherEntity=new WeatherEntity();
        weatherEntity.id=33;
        weatherEntity.temp= example.getMain().getTemp()-273;
        weatherEntity.dateTime="now";
        weatherEntity.wind=example.getWind().getSpeed();
        localDataSource.storeWeather(weatherEntity);
      //  Log.d("Лежит в базе", String.valueOf(example.getMain().getTemp()));
    }
    public LiveData<WeatherEntity> getOneDayWeatherData(String s){// 1 день
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Example weatherForecast = remoteDataSource.getWeatherDay(s);
                Log.i("Jane",weatherForecast.getMain().getTemp().toString());
                storeWeatherforDay(weatherForecast);
            }
        });
        return localDataSource.getWeatherforOneDay();
    }

}
