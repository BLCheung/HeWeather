package com.cityconstruction.blcheung.heweather.gson;

/**
 * Created by BLCheung.
 * Date:2018/3/26 21:05
 */

/**
 * cond_code_d : 103
 * cond_code_n : 101
 * cond_txt_d : 晴间多云
 * cond_txt_n : 多云
 * date : 2018-03-26
 * hum : 67
 * mr : 13:30
 * ms : 02:12
 * pcpn : 0.0
 * pop : 0
 * pres : 1020
 * sr : 06:25
 * ss : 18:40
 * tmp_max : 25
 * tmp_min : 19
 * uv_index : 9
 * vis : 10
 * wind_deg : 0
 * wind_dir : 无持续风向
 * wind_sc : 1-2
 * wind_spd : 11
 */

/**
 * 最近几天天气预报
 */
public class WeatherDailyForecast {

    /**
     * 白天天气状况代码
     */
    private String cond_code_d;
    /**
     * 晚间天气状况代码
     */
    private String cond_code_n;
    /**
     * 白天天气状况描述
     */
    private String cond_txt_d;
    /**
     * 晚间天气状况描述
     */
    private String cond_txt_n;
    /**
     * 预报日期
     */
    private String date;
    /**
     * 相对湿度
     */
    private String hum;
    /**
     * 月升时间
     */
    private String mr;
    /**
     * 月落时间
     */
    private String ms;
    /**
     * 降水量
     */
    private String pcpn;
    /**
     * 降水概率
     */
    private String pop;
    /**
     * 大气压强
     */
    private String pres;
    /**
     * 日出时间
     */
    private String sr;
    /**
     * 日落时间
     */
    private String ss;
    /**
     * 最高温度
     */
    private String tmp_max;
    /**
     * 最低温度
     */
    private String tmp_min;
    /**
     * 紫外线强度指数
     */
    private String uv_index;
    /**
     * 能见度，单位：公里
     */
    private String vis;
    /**
     * 风向360角度
     */
    private String wind_deg;
    /**
     * 风向
     */
    private String wind_dir;
    /**
     * 风力
     */
    private String wind_sc;
    /**
     * 风速，公里/小时
     */
    private String wind_spd;

    public String getCond_code_d() {
        return cond_code_d;
    }

    public void setCond_code_d(String cond_code_d) {
        this.cond_code_d = cond_code_d;
    }

    public String getCond_code_n() {
        return cond_code_n;
    }

    public void setCond_code_n(String cond_code_n) {
        this.cond_code_n = cond_code_n;
    }

    public String getCond_txt_d() {
        return cond_txt_d;
    }

    public void setCond_txt_d(String cond_txt_d) {
        this.cond_txt_d = cond_txt_d;
    }

    public String getCond_txt_n() {
        return cond_txt_n;
    }

    public void setCond_txt_n(String cond_txt_n) {
        this.cond_txt_n = cond_txt_n;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTmp_max() {
        return tmp_max;
    }

    public void setTmp_max(String tmp_max) {
        this.tmp_max = tmp_max;
    }

    public String getTmp_min() {
        return tmp_min;
    }

    public void setTmp_min(String tmp_min) {
        this.tmp_min = tmp_min;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(String wind_deg) {
        this.wind_deg = wind_deg;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public String getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(String wind_spd) {
        this.wind_spd = wind_spd;
    }
}
