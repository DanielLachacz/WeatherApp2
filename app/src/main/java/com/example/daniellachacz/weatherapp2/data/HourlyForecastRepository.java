package com.example.daniellachacz.weatherapp2.data;

import android.util.Log;

import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.Forecast;
import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List;
import com.example.daniellachacz.weatherapp2.data.network.ApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class HourlyForecastRepository {

    private ApiInterface apiInterface;
    private String appid;
    private MutableLiveData<ArrayList<List>> forecast = new MutableLiveData<>();

    @Inject
    public HourlyForecastRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public MutableLiveData<Forecast> getHourlyForecast(String q, String appid, String units) {

        final MutableLiveData<Forecast> data = new MutableLiveData<>();
        apiInterface.getHourlyForecast(q, appid, units).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                ArrayList<List> arrayList = response.body().getList();
                data.postValue(response.body());
                forecast.postValue(arrayList);


                Log.d("HFR onResponse ", String.valueOf(arrayList));
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("HFR onFailure ", t.getMessage());
            }
        });
        return data;
    }


    public LiveData<ArrayList<List>> getForecast() {
        return forecast;
    }
}
