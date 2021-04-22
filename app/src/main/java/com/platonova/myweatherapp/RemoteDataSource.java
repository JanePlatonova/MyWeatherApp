package com.platonova.myweatherapp;

import android.util.Log;

import com.platonova.myweatherapp.Data.Example;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private MainActivity.WeatherOneDayApi weatherService;

    public RemoteDataSource(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherService = retrofit.create(MainActivity.WeatherOneDayApi.class);
    }

    public Example getWeatherDay(String s){
        Call<Example> call = weatherService.getWeatherByCityName(s,"3d822b9dce4e57f12b9b3400d480a358");
        try {
            Response<Example> response = call.execute();
            if(response.isSuccessful()){
                Log.d("RemoteData", String.valueOf(response.body().getMain().getTemp()));
                return response.body();

            }
        }catch(IOException ioex){
            Log.e("Remote", "IOEX: " + ioex.toString());
        }

        return null;
    }


}
