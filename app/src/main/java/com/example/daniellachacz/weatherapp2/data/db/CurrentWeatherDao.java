package com.example.daniellachacz.weatherapp2.data.db;

import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CurrentWeatherDao {

    @Query("SELECT * FROM current_weather WHERE id = :idKey")
    LiveData<Weather> load(int idKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Weather weather);
}
