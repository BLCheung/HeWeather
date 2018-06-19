package com.cityconstruction.blcheung.heweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/26 22:53
 */

/**
 * AQI站点实况
 */
public class AirNowStation {

    /**
     * air_sta : 广雅中学
     * aqi : 85
     * asid : CNA1345
     * co : 1.0
     * lat : 23.1422
     * lon : 113.235
     * main : PM2.5
     * no2 : 90
     * o3 : 39
     * pm10 : 90
     * pm25 : 63
     * pub_time : 2018-03-26 20:00
     * qlty : 良
     * so2 : 14
     */

    /**
     * 站点名称
     */
    private String air_sta;
    /**
     * 空气质量指数
     */
    private String aqi;
    /**
     * 站点ID
     */
    private String asid;
    /**
     * 一氧化碳
     */
    private String co;
    /**
     * 站点纬度
     */
    private String lat;
    /**
     * 站点经度
     */
    private String lon;
    /**
     * 主要污染物
     */
    private String main;
    /**
     * 二氧化氮
     */
    private String no2;
    /**
     * 臭氧
     */
    private String o3;
    /**
     * pm10
     */
    private String pm10;
    /**
     * pm25
     */
    private String pm25;
    /**
     * 数据发布时间,格式yyyy-MM-dd HH:mm
     */
    private String pub_time;
    /**
     * 空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
     */
    private String qlty;
    /**
     * 二氧化硫
     */
    private String so2;

    public String getAir_sta() {
        return air_sta;
    }

    public void setAir_sta(String air_sta) {
        this.air_sta = air_sta;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAsid() {
        return asid;
    }

    public void setAsid(String asid) {
        this.asid = asid;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
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

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }
}
