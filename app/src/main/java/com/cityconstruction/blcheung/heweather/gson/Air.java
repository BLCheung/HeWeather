package com.cityconstruction.blcheung.heweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BLCheung.
 * Date:2018/3/26 23:18
 */

/**
 * 空气质量数据集合
 */
public class Air {
    private String status;
    @SerializedName("basic")
    private AirBasic airBasic;

    @SerializedName("update")
    private AirUpdate airUpdate;

    @SerializedName("air_now_city")
    private AirNowCity airNowCity;

    @SerializedName("air_now_station")
    private List<AirNowStation> airNowStationList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirBasic getAirBasic() {
        return airBasic;
    }

    public void setAirBasic(AirBasic airBasic) {
        this.airBasic = airBasic;
    }

    public AirUpdate getAirUpdate() {
        return airUpdate;
    }

    public void setAirUpdate(AirUpdate airUpdate) {
        this.airUpdate = airUpdate;
    }

    public AirNowCity getAirNowCity() {
        return airNowCity;
    }

    public void setAirNowCity(AirNowCity airNowCity) {
        this.airNowCity = airNowCity;
    }

    public List<AirNowStation> getAirNowStationList() {
        return airNowStationList;
    }

    public void setAirNowStationList(List<AirNowStation> airNowStationList) {
        this.airNowStationList = airNowStationList;
    }
}
