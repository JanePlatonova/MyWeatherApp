package com.platonova.myweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.platonova.myweatherapp.Data.Cat;
import com.platonova.myweatherapp.Data.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText;
        Button button1;
        Button button2;
        ImageView imageView=findViewById(R.id.image1);
        new ImageDownloader(imageView).execute("https://i.ebayimg.com/images/g/fkwAAMXQya1Q7h1o/s-l400.jpg");
        //imageView.setImageURI(Uri.parse("https://purr.objects-us-east-1.dream.io/i/img_20160427_142023.jpg"));
      //  Log.i("Jane", String.valueOf(Uri.parse("https://random.dog/")));
        editText=findViewById(R.id.edit);
        button1=findViewById(R.id.but1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("Key",editText.getText().toString());
                startActivity(intent);
            }
        });


        button2=findViewById(R.id.but2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://aws.random.cat")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                CatPhotoService catPhoto;
                catPhoto = retrofit.create(CatPhotoService.class);

                catPhoto.getcatphoto().
                enqueue(new Callback<Cat>() {
                    @Override
                    public void onResponse(Call<Cat> call, Response<Cat> response) {
                        if (response.isSuccessful()) {
                            Log.i("Jane", response.body().getFile());
                            new ImageDownloader(imageView).execute(response.body().getFile());
                            Log.i("Jane", "OK");
                        } else Log.i("Jane", "no reponse");
                    }
                    @Override
                    public void onFailure(Call<Cat> call, Throwable t) {
                        Log.i("Jane","Failure "+t);
                    }
                });
            }
        });

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MessageAPI messageAPI=retrofit.create(MessageAPI.class);
        Call<String> message=messageAPI.message();
        message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              Log.i("Jane1",""+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                    Log.i("Jane","Failure"+t);
            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.openweathermap.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        WeatherOneDayApi weatherOneDayApi;
//        weatherOneDayApi=retrofit.create(WeatherOneDayApi.class);
//        weatherOneDayApi.getWeatherByCityName("Kiev,ua","3d822b9dce4e57f12b9b3400d480a358").
//                enqueue(new Callback<Example>() {
//                    @Override
//                    public void onResponse(Call<Example> call, Response<Example> response) {
//                        if (response.isSuccessful()) {
//                            Log.i("Jane", response.body().getWind().getSpeed().toString());
//                            Log.i("Jane", "OK");
//                        } else Log.i("Jane", "no reponse");
//                    }
//                    @Override
//                    public void onFailure(Call<Example> call, Throwable t) {
//                        Log.i("Jane","Failure "+t);
//                    }
//                });
//        Log.i("Vain",retrofit.toString());
//        Log.i("MainAct1","Create");
            }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainAct1","ONSTOP");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainAct1","Resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainAct1","Убили");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainAct1","Pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainAct1","Restart act");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainAct1","start");
    }

    public interface MessageAPI{
        @GET("https://api.openweathermap.org/data/2.5/forecast?q=London&appid=3d822b9dce4e57f12b9b3400d480a358")
        Call<String> message();
    }


        public interface WeatherOneDayApi {
            @GET("/data/2.5/weather")
            Call<Example> getWeatherByCityName(@Query("q") String city, @Query("appid") String appID);

        }
        public interface MyEx{
        @GET("/data/2.5/weather")
            Call<Example> getmy(@Query("q") String city, @Query("appid") String appID);
        }
        public interface CatPhotoService{
        @GET("/meow")
        Call<Cat> getcatphoto();
    }
//    public interface WeatherService1 {
//        @GET("/data/2.5/weather")
//        Call<String> getWeatherByCityName(@Query("q") String city, @Query("appid") String appID);
//    }
}