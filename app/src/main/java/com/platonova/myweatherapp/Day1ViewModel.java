package com.platonova.myweatherapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.platonova.myweatherapp.Data.WeatherEntity;

public class Day1ViewModel extends AndroidViewModel {
    private Repository repository;
    public String cityName;
    private LiveData<WeatherEntity> weatherData;

    public Day1ViewModel (Application application) {
        super(application);
        repository = new Repository(application);
    }

    LiveData<WeatherEntity> getWeatherData(String s) {
        cityName=s;
        weatherData = repository.getOneDayWeatherData(cityName);
        return weatherData;
    }

}
