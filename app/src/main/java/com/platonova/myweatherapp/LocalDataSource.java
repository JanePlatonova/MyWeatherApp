package com.platonova.myweatherapp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.platonova.myweatherapp.Data.Example;
import com.platonova.myweatherapp.Data.WeatherDB;
import com.platonova.myweatherapp.Data.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

public class LocalDataSource {
    final WeatherDB db;

    public LocalDataSource(Context context){
        db = Room.databaseBuilder(context, WeatherDB.class,"weather").fallbackToDestructiveMigration().build();
    }
    public void storeWeather(WeatherEntity we) {
        db.weatherDao().insertWeather(we);
    }

    public LiveData<List<WeatherEntity>> getWeatherfor4Day(){
       //  Log.d("Запрос из базы", String.valueOf(db.weatherDao().getWeatherForecast().getValue().get(0).id));
        return db.weatherDao().getWeatherForecast();
    }
    public LiveData<WeatherEntity> getWeatherforOneDay(){
         //Log.d("Запрос из базы", String.valueOf(db.weatherDao().getOneDayWeather().getValue().id));
        return db.weatherDao().getOneDayWeather();
    }


}
