package com.platonova.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.platonova.myweatherapp.Data.Example;
import com.platonova.myweatherapp.Data.WeatherDB;
import com.platonova.myweatherapp.Data.WeatherEntity;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {
  //private Repository repository;
   LiveData<WeatherEntity> weatherData;

   private Day1ViewModel day1ViewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show();
                Log.i("menu","About");
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                Log.i("menu","Setting");
                break;

            case R.id.menuWeekWeather:
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show();
                Log.i("menu","Week weather");
                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("Key",day1ViewModel.cityName);
                startActivity(intent);
                break;

        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s=getIntent().getExtras().get("Key").toString();
        EditText editTextcity=findViewById(R.id.editcityname);
        editTextcity.setText(s);
        EditText wind=findViewById(R.id.wind);
        EditText temp=findViewById(R.id.temp);

        day1ViewModel = ViewModelProviders.of(this).get(Day1ViewModel.class);
        weatherData=day1ViewModel.getWeatherData(s);
        weatherData.observe(this, new Observer<WeatherEntity>() {
                    @Override
                    public void onChanged(WeatherEntity weatherEntities) {
                        if ((weatherData.getValue() != null)) {
                            Log.i("Jane temp", String.valueOf(weatherData.getValue().temp));
                            wind.setText(String.valueOf(weatherData.getValue().wind));
                            temp.setText(String.valueOf(weatherData.getValue().temp));
                        }
                    }
                });
//        repository=new Repository(this);
//        weatherData=repository.getOneDayWeatherData(s);
//        weatherData.observe(this, new Observer<WeatherEntity>() {
//            @Override
//            public void onChanged(WeatherEntity weatherEntities) {
//                if (weatherData!=null) {
//                    Log.i("Jane temp", String.valueOf(weatherData.getValue().temp));
//                    wind.setText( String.valueOf(weatherData.getValue().wind));
//
////            }            temp.setText(String.valueOf(weatherData.getValue().temp));
//                }
//        });




//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.openweathermap.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        WeatherOneDayApiService weatherOneDayApi;
//        weatherOneDayApi = retrofit.create(WeatherOneDayApiService.class);
//        weatherOneDayApi.getWeatherByCityName(s, "3d822b9dce4e57f12b9b3400d480a358").
//                enqueue(new Callback<Example>() {
//                    @Override
//                    public void onResponse(Call<Example> call, Response<Example> response) {
//                        if (response.isSuccessful()) {
//                            wind.setText(response.body().getWind().getSpeed().toString());
//                            Double t=(response.body().getMain().getTemp()-273);
//                            temp.setText(t.toString());
//                            Log.i("Jane2", response.body().getWind().getSpeed().toString());
//                            Log.i("Jane2", "OK");
//                            RoomDatabase database= Room.databaseBuilder(getApplicationContext(),WeatherDB.class, "weather").
//                                    fallbackToDestructiveMigration().build();
////                            RoomDatabase database= Room.databaseBuilder(getApplicationContext(),WeatherDB.class, "weather").
////                                    allowMainThreadQueries().build();
//                            Executors.newSingleThreadExecutor().execute(new Runnable() {
//                                @Override
//                                public void run() {
//                                    WeatherEntity weatherEntity=new WeatherEntity();
//                                    weatherEntity.id=33;
//                                    weatherEntity.temp= response.body().getMain().getTemp()-273;
//                                    weatherEntity.dateTime="now";
//                                    weatherEntity.wind=response.body().getWind().getSpeed();
//                                    ((WeatherDB)database).weatherDao().insertWeather(weatherEntity);
//                                }});
//
//
//
//                            Log.i("Jane", "in DB");
//                        } else Log.i("Jane2", "no reponse");
//                    }
//
//                    @Override
//                    public void onFailure(Call<Example> call, Throwable t) {
//                        Log.i("Jane2", "Failure " + t);
//                    }
//                });
//        Log.i("Vain", retrofit.toString());
//        Log.i("MainAct1", "Create");
//

    }


}