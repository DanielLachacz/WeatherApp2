package com.example.daniellachacz.weatherapp2.data;


import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;
import com.example.daniellachacz.weatherapp2.data.network.ApiInterface;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class CurrentWeatherRepository {

    private final ApiInterface apiInterface;
    private String appid;
    private MutableLiveData<Weather> weather = new MutableLiveData<>();

    @Inject
    CurrentWeatherRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public LiveData<Weather> getCurrentWeather(String q, String appid, String units) {

        final MutableLiveData<Weather> data = new MutableLiveData<>();
        apiInterface.getCurrentWeather(q, appid, units).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                data.setValue(response.body());
                weather.postValue(data.getValue());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<Weather> getWeather() {
        return weather;
    }
}
