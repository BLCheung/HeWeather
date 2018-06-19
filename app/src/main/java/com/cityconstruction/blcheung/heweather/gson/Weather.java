package com.cityconstruction.blcheung.heweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BLCheung.
 * Date:2018/3/26 23:09
 */

/**
 * 常规天气数据集合
 */
public class Weather {
    private String status;
    @SerializedName("basic")
    private WeatherBasic weatherBasic;
    @SerializedName("update")
    private WeatherUpdate weatherUpdate;
    private Now now;
    @SerializedName("daily_forecast")
    private List<WeatherDailyForecast> weatherDailyForecastList;
    @SerializedName("lifestyle")
    private List<WeatherLifeStyle> weatherLifeStyleList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WeatherBasic getWeatherBasic() {
        return weatherBasic;
    }

    public void setWeatherBasic(WeatherBasic weatherBasic) {
        this.weatherBasic = weatherBasic;
    }

    public WeatherUpdate getWeatherUpdate() {
        return weatherUpdate;
    }

    public void setWeatherUpdate(WeatherUpdate weatherUpdate) {
        this.weatherUpdate = weatherUpdate;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public List<WeatherDailyForecast> getWeatherDailyForecastList() {
        return weatherDailyForecastList;
    }

    public void setWeatherDailyForecastList(List<WeatherDailyForecast> weatherDailyForecastList) {
        this.weatherDailyForecastList = weatherDailyForecastList;
    }

    public List<WeatherLifeStyle> getWeatherLifeStyleList() {
        return weatherLifeStyleList;
    }

    public void setWeatherLifeStyleList(List<WeatherLifeStyle> weatherLifeStyleList) {
        this.weatherLifeStyleList = weatherLifeStyleList;
    }
}
