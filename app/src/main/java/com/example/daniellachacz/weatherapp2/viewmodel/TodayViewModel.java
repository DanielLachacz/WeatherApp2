package com.example.daniellachacz.weatherapp2.viewmodel;


import com.example.daniellachacz.weatherapp2.data.CurrentWeatherRepository;
import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;

import javax.inject.Inject;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TodayViewModel extends ViewModel {

    private CurrentWeatherRepository currentWeatherRepository;
    public final ObservableField<String> city = new ObservableField<>();
    private String KEY = "52e6ff60bba8613b4850e065dcd3d0ac";
    private String UNITS = "metric";


    @Inject
    public TodayViewModel(CurrentWeatherRepository currentWeatherRepository) {
        this.currentWeatherRepository = currentWeatherRepository;
    }

    public void getCity() {
        currentWeatherRepository.getCurrentWeather(String.valueOf(city.get()), KEY, UNITS);
    }

    public LiveData<Weather> getWeather() {
        return currentWeatherRepository.getWeather();
    }
}
