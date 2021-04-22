package com.platonova.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.platonova.myweatherapp.Data.Example;
import com.platonova.myweatherapp.Data.WeekWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity3 extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
   public MyRecyclerViewAdapter adapter;
   EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        String s="London";
        editText=findViewById(R.id.city3);
        editText.setText(s);
        RecyclerView recyclerView;
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        WeatherWeekService weatherWeekService;
        weatherWeekService = retrofit.create(WeatherWeekService.class);
        weatherWeekService.getWeatherByCityName(s, "3d822b9dce4e57f12b9b3400d480a358").
                enqueue(new Callback<WeekWeather>() {
                    @Override
                    public void onResponse(Call<WeekWeather> call, Response<WeekWeather> response) {
                        if (response.isSuccessful()) {
                            Log.i("Jane", response.body().getCity().getName().toString());
                            adapter = new MyRecyclerViewAdapter(getApplicationContext(),  response.body().getList());
                            for(int i=0;i<4;i++) {
//                                Integer t= Integer.valueOf(response.body().getList().get(i).getMain().getTemp().toString());
                                Log.i("Jane temp",   ""+(Double.valueOf(response.body().getList().get(i).getMain().getTemp().toString())-273));
                                Log.i("Jane wind",  response.body().getList().get(i).getWind().getSpeed().toString());
                                Log.i("Jane pressure",  response.body().getList().get(i).getMain().getPressure().toString());
                            }
                            adapter.setClickListener(MainActivity3.this::onItemClick);
                            recyclerView.setAdapter(adapter);

                            Log.i("Jane", "OK");
                        } else Log.i("Jane", "no reponse");
                    }

                    @Override
                    public void onFailure(Call<WeekWeather> call, Throwable t) {
                        Log.i("Jane", "Failure " + t);
                    }
                });



    }
    @Override
    public void onItemClick(View view, int position) {
        Log.i("Jane","You clicked " + adapter.getItem(position).getWeather().get(0).getDescription() );
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}