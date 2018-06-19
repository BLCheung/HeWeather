package com.cityconstruction.blcheung.heweather;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cityconstruction.blcheung.heweather.gson.Air;
import com.cityconstruction.blcheung.heweather.gson.Weather;
import com.cityconstruction.blcheung.heweather.gson.WeatherDailyForecast;
import com.cityconstruction.blcheung.heweather.gson.WeatherLifeStyle;
import com.cityconstruction.blcheung.heweather.ui.ChooseAreaFragment;
import com.cityconstruction.blcheung.heweather.util.HttpUtil;
import com.cityconstruction.blcheung.heweather.util.JSONHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    public static final String PREFS_WEATHER = "prefs_weather";
    public static final String PREFS_AIR = "prefs_air";
    public static final String PREFS_BINGPIC = "prefs_bingpic";
    private final String STATUS_OK = "ok";
    private final String TAG = getClass().getSimpleName();
    /**
     * weather.xml
     */
    private DrawerLayout dlWeatherLayout;
    private ScrollView svWeatherLayout;
    private SwipeRefreshLayout slSwipeRefreshLayout;
    private Button btnNav;
    private ImageView ivBingPic;
    private TextView tvTitleCityName;
    private TextView tvTitleLocalTime;
    /**
     * now.xml
     */
    private TextView tvNowDegree, tvNowInfo;
    /**
     * daily_forecast.xml
     */
    private LinearLayout llDailyForecastLayout;
    /**
     * aqi.xml
     */
    private TextView tvAqiAQI, tvAqiPM25;
    /**
     * lifestyle.xml
     */
    private TextView tvLifestyleComf, tvLifestyleAir, tvLifestyleUv, tvLifestyleDrsg;
    private TextView tvLifestyleSport, tvLifestyleFlu, tvLifestyleTrav, tvLifestyleCw;
    private TextView tvNowCityCo;
    private TextView tvNowCityMain;
    private TextView tvNowCityNo2;
    private TextView tvNowCitySo2;
    private TextView tvNowCityO3;
    private TextView tvNowCityPM10;
    private TextView tvNowCityQlty;
    private TextView tvNowCityTime;
    private SharedPreferences prefs;
    private String weatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT > 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        initViews();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherPrefs = prefs.getString(PREFS_WEATHER, null);
        String airPrefs = prefs.getString(PREFS_AIR, null);
        String bingPicPrefs = prefs.getString(PREFS_BINGPIC, null);
        if (weatherPrefs != null) {
            Weather weatherData = JSONHandler.handlerWeatherResponse(weatherPrefs);
            weatherId = weatherData.getWeatherBasic().getWeatherId();
            showWeatherInfo(weatherData);
        } else if (airPrefs != null) {
            Air airData = JSONHandler.handlerAirResponse(airPrefs);
            showAirInfo(airData);
        } else if (bingPicPrefs != null) {
            Glide.with(WeatherActivity.this)
                    .load(bingPicPrefs)
                    .into(ivBingPic);
        } else {
            weatherId = getIntent().getStringExtra(ChooseAreaFragment.EXTRA_WEATHER_ID);
            showBingPic();
            requestWeather(weatherId);
            requestAir(weatherId);
        }

        slSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(
                WeatherActivity.this, R.color.colorPrimary));

        slSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showBingPic();
                requestWeather(ChooseAreaFragment.weatherId);
                requestAir(ChooseAreaFragment.weatherId);
            }
        });

        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlWeatherLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /**
     * 初始化
     */
    private void initViews() {
        dlWeatherLayout = findViewById(R.id.dl_weather_layout);
        svWeatherLayout = findViewById(R.id.sv_weather_layout);
        slSwipeRefreshLayout = findViewById(R.id.sl_swiperefresh_layout);
        btnNav = findViewById(R.id.btn_titlt_nav);
        ivBingPic = findViewById(R.id.iv_bingpic);
        tvTitleCityName = findViewById(R.id.tv_title_cityName);
        tvTitleLocalTime = findViewById(R.id.tv_title_localTime);
        tvNowDegree = findViewById(R.id.tv_now_degree);
        tvNowInfo = findViewById(R.id.tv_now_info);
        llDailyForecastLayout = findViewById(R.id.ll_daily_forecast_layout);
        tvAqiAQI = findViewById(R.id.tv_aqi_aqi);
        tvAqiPM25 = findViewById(R.id.tv_aqi_pm25);
        tvLifestyleComf = findViewById(R.id.tv_lifestyle_comf);
        tvLifestyleAir = findViewById(R.id.tv_lifestyle_air);
        tvLifestyleUv = findViewById(R.id.tv_lifestyle_uv);
        tvLifestyleDrsg = findViewById(R.id.tv_lifestyle_drsg);
        tvLifestyleSport = findViewById(R.id.tv_lifestyle_sport);
        tvLifestyleFlu = findViewById(R.id.tv_lifestyle_flu);
        tvLifestyleTrav = findViewById(R.id.tv_lifestyle_trav);
        tvLifestyleCw = findViewById(R.id.tv_lifestyle_cw);
        tvNowCityMain = findViewById(R.id.tv_now_city_main);
        tvNowCityCo = findViewById(R.id.tv_now_city_co);
        tvNowCityNo2 = findViewById(R.id.tv_now_city_no2);
        tvNowCitySo2 = findViewById(R.id.tv_now_city_so2);
        tvNowCityO3 = findViewById(R.id.tv_now_city_o3);
        tvNowCityPM10 = findViewById(R.id.tv_now_city_pm10);
        tvNowCityQlty = findViewById(R.id.tv_now_city_qlty);
        tvNowCityTime = findViewById(R.id.tv_now_city_time);
    }

    /**
     * 显示常规天气集合数据
     *
     * @param weatherData
     */
    private void showWeatherInfo(Weather weatherData) {
        if (weatherData != null && STATUS_OK.equals(weatherData.getStatus())) {
            // 城市名
            String cityName = weatherData.getWeatherBasic().getLocation();
            // 当地时间
            String locationTime = weatherData.getWeatherUpdate().getLoc().split(" ")[1];
            // 度数
            String degree = weatherData.getNow().getTmp() + "°C";
            // 天气状况描述
            String weatherInfo = weatherData.getNow().getCond_txt();
            tvTitleCityName.setText(cityName);
            tvTitleLocalTime.setText(locationTime);
            tvNowDegree.setText(degree);
            tvNowInfo.setText(weatherInfo);
            llDailyForecastLayout.removeAllViews();
            //DailyForecast
            // 遍历最近三天天气状况
            for (WeatherDailyForecast dailyForecast : weatherData.getWeatherDailyForecastList()) {
                // item模板
                View view = LayoutInflater.from(this)
                        .inflate(R.layout.daily_forecast_item, llDailyForecastLayout, false);
                TextView tvDailyDate = view.findViewById(R.id.tv_daily_date);
                ImageView ivDailyInfo = view.findViewById(R.id.iv_daily_info);
                TextView tvDailyMax = view.findViewById(R.id.tv_daily_max);
                TextView tvDailyMin = view.findViewById(R.id.tv_daily_min);
                // 日期
                tvDailyDate.setText(dailyForecast.getDate());
                // 天气状态logo
                Glide.with(WeatherActivity.this)
                        .load(getResources().getIdentifier("heweather"
                                        + dailyForecast.getCond_code_d(), "drawable",
                                getApplicationContext().getPackageName()))
                        .override(100, 100)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivDailyInfo);
                // 最高温度
                tvDailyMax.setText(dailyForecast.getTmp_max());
                // 最低温度
                tvDailyMin.setText(dailyForecast.getTmp_min());
                // 添加模板
                llDailyForecastLayout.addView(view);
            }
            // LifeStyle
            for (WeatherLifeStyle lifeStyle : weatherData.getWeatherLifeStyleList()) {
                switch (lifeStyle.getType()) {
                    case "comf":
                        tvLifestyleComf.setText("舒适度: " + lifeStyle.getTxt());
                        break;
                    case "air":
                        tvLifestyleAir.setText("空气指数: " + lifeStyle.getTxt());
                        break;
                    case "uv":
                        tvLifestyleUv.setText("紫外线指数: " + lifeStyle.getTxt());
                        break;
                    case "drsg":
                        tvLifestyleDrsg.setText("穿衣指数: " + lifeStyle.getTxt());
                        break;
                    case "sport":
                        tvLifestyleSport.setText("运动指数: " + lifeStyle.getTxt());
                        break;
                    case "flu":
                        tvLifestyleFlu.setText("感冒指数: " + lifeStyle.getTxt());
                        break;
                    case "trav":
                        tvLifestyleTrav.setText("旅游指数: " + lifeStyle.getTxt());
                        break;
                    case "cw":
                        tvLifestyleCw.setText("洗车指数: " + lifeStyle.getTxt());
                        break;
                    default:
                        break;
                }
            }
            svWeatherLayout.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(WeatherActivity.this, "显示天气信息失败!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示空气数据
     *
     * @param airData
     */
    private void showAirInfo(Air airData) {
        if (airData != null && STATUS_OK.equals(airData.getStatus())) {
            String aqi = airData.getAirNowCity().getAqi();
            String pm25 = airData.getAirNowCity().getPm25();
            String pm10 = airData.getAirNowCity().getPm10();
            String co = airData.getAirNowCity().getCo();
            String no2 = airData.getAirNowCity().getNo2();
            String so2 = airData.getAirNowCity().getSo2();
            String main = airData.getAirNowCity().getMain();
            String o3 = airData.getAirNowCity().getO3();
            String pubTime = airData.getAirNowCity().getPub_time();
            String qlty = airData.getAirNowCity().getQlty();
            // AQI
            tvAqiAQI.setText(aqi);
            tvAqiPM25.setText(pm25);
            // now_city.xml
            tvNowCityPM10.setText(pm10);
            tvNowCityCo.setText(co);
            tvNowCityNo2.setText(no2);
            tvNowCitySo2.setText(so2);
            tvNowCityMain.setText(main);
            tvNowCityO3.setText(o3);
            tvNowCityTime.setText(pubTime);
            tvNowCityQlty.setText(qlty);
//
        } else {
            Toast.makeText(WeatherActivity.this,
                    "显示空气质量数据失败!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取必应每日一图
     */
    private void showBingPic() {
        String url = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "无法获取到必应每日一图!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPicData = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString(PREFS_BINGPIC, bingPicData);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this)
                                .load(bingPicData)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivBingPic);
                    }
                });
            }


        });
    }

    /**
     * 根据weatherId从服务器请求天气数据信息
     *
     * @param weatherId
     */
    private void requestWeather(String weatherId) {
        String url = "https://free-api.heweather.com/s6/weather?location=" + weatherId
                + "&key=6ba8a253abf14468ad6a1b5e85ca0fb6";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String weatherResponse = response.body().string();
                final Weather weatherData = JSONHandler.handlerWeatherResponse(weatherResponse);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherData != null && STATUS_OK.equals(weatherData.getStatus())) {
                            // 缓存到本地
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString(PREFS_WEATHER, weatherResponse);
                            editor.apply();
                            showWeatherInfo(weatherData);
                        } else {
                            Toast.makeText(WeatherActivity.this,
                                    "服务器返回天气信息有误或数据解析错误! "
                                            + weatherData.getStatus(), Toast.LENGTH_SHORT).show();
                            slSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,
                                "服务器无响应,无法获取天气到数据!", Toast.LENGTH_SHORT).show();
                        slSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    /**
     * 根据weatherId从服务器请求空气质量数据信息
     *
     * @param weatherId
     */
    private void requestAir(String weatherId) {
        String url = "https://free-api.heweather.com/s6/air?location=" + weatherId
                + "&key=6ba8a253abf14468ad6a1b5e85ca0fb6";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String airResponse = response.body().string();
                Log.d(TAG, "onResponse: " + airResponse);
                final Air airData = JSONHandler.handlerAirResponse(airResponse);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (airData != null && STATUS_OK.equals(airData.getStatus())) {
                            // 缓存
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString(PREFS_AIR, airResponse);
                            editor.apply();
                            showAirInfo(airData);
                        } else {
                            Toast.makeText(WeatherActivity.this,
                                    "服务器返回空气信息有误或数据解析错误! "
                                            + airData.getStatus(), Toast.LENGTH_SHORT).show();
                        }
                        slSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,
                                "无法获取到空气质量信息!", Toast.LENGTH_SHORT).show();
                        slSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }
}
