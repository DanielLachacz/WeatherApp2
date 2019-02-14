package com.example.daniellachacz.weatherapp2.data.db;

import android.content.Context;

import com.example.daniellachacz.weatherapp2.data.model.currentWeather.Weather;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Weather.class}, version = 1)
public abstract class CurrentWeatherDatabase extends RoomDatabase {

    /* As you can see I implemented Room Database stuff to the data cache.
       Currently, the application uses the cache provided by Retrofit.
       In future I want to change Retrofit cache on a Room cache.
     */

    public static CurrentWeatherDatabase instance;

    public abstract CurrentWeatherDao currentWeatherDao();

    public static synchronized CurrentWeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
            CurrentWeatherDatabase.class, "current_weather")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
