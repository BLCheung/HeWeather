package com.cityconstruction.blcheung.heweather.gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

/**
 * Created by BLCheung.
 * Date:2018/3/26 20:23
 */

/**
 * weatherId : CN101280101
 * location : 广州
 * parent_city : 广州
 * admin_area : 广东
 * cnty : 中国
 * lat : 23.12517738
 * lon : 113.28063965
 * tz : +8.00
 */

/**
 * 基础信息
 */
public class WeatherBasic {
    /**
     * 地区／城市ID
     */
    @SerializedName("weatherId")
    private String weatherId;
    /**
     * 地区／城市名称
     */
    private String location;
    /**
     * 该地区／城市的上级城市
     */
    private String parent_city;
    /**
     * 该地区／城市所属行政区域
     */
    private String admin_area;
    /**
     * 该地区／城市所属国家名称
     */
    private String cnty;
    /**
     * 地区／城市经度
     */
    private String lat;
    /**
     * 地区／城市纬度
     */
    private String lon;
    /**
     * 该地区／城市所在时区
     */
    private String tz;

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParent_city() {
        return parent_city;
    }

    public void setParent_city(String parent_city) {
        this.parent_city = parent_city;
    }

    public String getAdmin_area() {
        return admin_area;
    }

    public void setAdmin_area(String admin_area) {
        this.admin_area = admin_area;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}
