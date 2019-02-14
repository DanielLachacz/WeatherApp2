package com.example.daniellachacz.weatherapp2.viewmodel;


import com.example.daniellachacz.weatherapp2.data.HourlyForecastRepository;
import com.example.daniellachacz.weatherapp2.data.model.forecastWeather.List;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class HourlyViewModel extends ViewModel {

    private HourlyForecastRepository hourlyForecastRepository;
    public final ObservableField<String> location = new ObservableField<>();
    private LiveData<ArrayList<List>> allWeather;
    private String KEY = "52e6ff60bba8613b4850e065dcd3d0ac";
    private String UNITS = "metric";

    @Inject
    public HourlyViewModel(HourlyForecastRepository hourlyForecastRepository) {
        this.hourlyForecastRepository = hourlyForecastRepository;
    }

    public void getCity() {
        hourlyForecastRepository.getHourlyForecast(String.valueOf(location.get()), KEY, UNITS);
    }

    public LiveData<ArrayList<List>> getForecast() {
        return hourlyForecastRepository.getForecast();
    }

}
