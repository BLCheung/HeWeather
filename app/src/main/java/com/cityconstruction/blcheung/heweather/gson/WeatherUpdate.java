package com.cityconstruction.blcheung.heweather.gson;

import org.litepal.crud.DataSupport;

/**
 * Created by BLCheung.
 * Date:2018/3/26 20:33
 */

/**
 * loc : 2018-03-26 19:47
 * utc : 2018-03-26 11:47
 */

/**
 * 接口更新时间
 */
public class WeatherUpdate {

    /**
     * 当地时间，24小时制，格式yyyy-MM-dd HH:mm
     */
    private String loc;
    /**
     * UTC时间，24小时制，格式yyyy-MM-dd HH:mm
     */
    private String utc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
