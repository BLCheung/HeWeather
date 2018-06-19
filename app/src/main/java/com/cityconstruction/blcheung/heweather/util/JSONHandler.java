package com.cityconstruction.blcheung.heweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.cityconstruction.blcheung.heweather.db.City;
import com.cityconstruction.blcheung.heweather.db.County;
import com.cityconstruction.blcheung.heweather.db.Province;
import com.cityconstruction.blcheung.heweather.gson.Air;
import com.cityconstruction.blcheung.heweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by BLCheung.
 * Date:2018/3/25 17:25
 */

public class JSONHandler {
    public static final String TAG = "JSONHandler";

    /**
     * 解析服务器返回的常规天气数据集合
     *
     * @param response
     * @return
     */
    public static Weather handlerWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray heWeatherArray = jsonObject.getJSONArray("HeWeather6");
            String weatherData = heWeatherArray.getJSONObject(0).toString();
            Weather weather = new Gson().fromJson(weatherData, Weather.class);
            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析服务器返回的空气质量数据集合
     *
     * @param responseData
     * @return
     */
    public static Air handlerAirResponse(String responseData) {
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONArray heWeatherArray = jsonObject.getJSONArray("HeWeather6");
            String airData = heWeatherArray.getJSONObject(0).toString();
            Air air = new Gson().fromJson(airData, Air.class);
            return air;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析服务器返回的省级JSON数据
     *
     * @param response 返回的数据
     * @return
     */
    public static boolean handlerProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的市级JSON数据
     *
     * @param response   返回的数据
     * @param provinceId 需要被请求的省级id
     * @return
     */
    public static boolean handlerCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的县/区级JSON数据
     *
     * @param response 返回的数据
     * @param cityId   需要被请求的县/区级id
     * @return
     */
    public static boolean handlerCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
