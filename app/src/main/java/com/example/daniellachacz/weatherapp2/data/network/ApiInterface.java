package com.example.daniellachacz.weatherapp2.data.network;

import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.Forecast;
import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather")
    Call<Weather> getCurrentWeather(
            @Query("q") String name,
            @Query("appid") String appid,
            @Query("units") String units);

    @GET("forecast")
    Call<Forecast> getHourlyForecast(
            @Query("q") String name,
            @Query("appid") String appid,
            @Query("units") String units);
}
