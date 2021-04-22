package com.platonova.myweatherapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertWeather(WeatherEntity weatherEntity);

    @Query("Select * FROM WeatherEntity where dateTime='now'")
    public LiveData<WeatherEntity> getOneDayWeather();

    @Query("SELECT * FROM WeatherEntity where dateTime!='1'")
    public LiveData<List<WeatherEntity>> getWeatherForecast();

}
