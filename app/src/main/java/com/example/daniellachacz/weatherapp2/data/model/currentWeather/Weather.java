
package com.example.daniellachacz.weatherapp2.data.model.currentWeather;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "current_weather")
public class Weather {

    @SerializedName("weather")
    @Expose
    @Embedded(prefix = "weather_")
    private ArrayList<Weather_> weather;
    @SerializedName("main")
    @Expose
    @Embedded(prefix = "main_")
    private Main main;
    @SerializedName("wind")
    @Expose
    @Embedded(prefix = "wind_")
    private Wind wind;
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

    @PrimaryKey(autoGenerate = true)
    private int idKey;
   // public static final int CURRENT_WEATHER_ID = 0;

    public int getIdKey() {
        return idKey;
    }

    public void setIdKey(int idKey) {
        this.idKey = idKey;
    }

    public String getDescription() {
        return weather.get(0).getDescription();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return weather.get(0).getIcon(); }

//     public String setIcon() {
//        return weather.set(0).setIcon();
//     }

    public String getIconUrl() {
        return "http://openweathermap.org/img/w/" + weather.get(0).getIcon() + ".png"; }


    public ArrayList<Weather_> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather_> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
