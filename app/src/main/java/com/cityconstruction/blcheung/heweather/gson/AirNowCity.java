package com.cityconstruction.blcheung.heweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/26 22:45
 */

/**
 * AQI城市实况
 */
public class AirNowCity {

    /**
     * aqi : 74
     * qlty : 良 √
     * main : PM2.5
     * pm25 : 54
     * pm10 : 83
     * no2 : 64
     * so2 : 11
     * co : 0.89
     * o3 : 55
     * pub_time : 2018-03-26 21:00
     */

    /**
     * 空气质量指数
     **/
    private String aqi;
    /**
     * 空气质量，取值范围:优，良，轻度污染，中度污染，重度污染，严重污染
     */
    private String qlty;
    /**
     * 主要污染物
     */
    private String main;
    /**
     * pm25
     */
    private String pm25;
    /**
     * pm10
     */
    private String pm10;
    /**
     * 二氧化氮
     */
    private String no2;
    /**
     * 二氧化硫
     */
    private String so2;
    /**
     * 一氧化碳
     */
    private String co;
    /**
     * 臭氧
     */
    private String o3;
    /**
     * 数据发布时间,格式yyyy-MM-dd HH:mm
     */
    private String pub_time;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getQlty() {
        return qlty;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }
}
