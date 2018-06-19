package com.cityconstruction.blcheung.heweather.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cityconstruction.blcheung.heweather.R;
import com.cityconstruction.blcheung.heweather.WeatherActivity;
import com.cityconstruction.blcheung.heweather.db.City;
import com.cityconstruction.blcheung.heweather.db.County;
import com.cityconstruction.blcheung.heweather.db.Province;
import com.cityconstruction.blcheung.heweather.util.HttpUtil;
import com.cityconstruction.blcheung.heweather.util.JSONHandler;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by BLCheung.
 * Date:2018/3/25 17:46
 */

public class ChooseAreaFragment extends Fragment {
    /**
     * 省级
     */
    public static final int LEVEL_PROVINCE = 0;
    /**
     * 市级
     */
    public static final int LEVEL_CITY = 1;
    /**
     * 县/区级
     */
    public static final int LEVEL_CONTY = 2;
    /**
     * 当前级别
     */
    private int currentLevel;
    /**
     * 选中的省份
     */
    private Province selectProvince;
    /**
     * 选中的城市
     */
    private City selectCity;
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private List<String> dataList = new ArrayList<>();
    public static final String EXTRA_WEATHER_ID = "weather_id";

    private TextView tvTitle;
    private Button btnBack;
    private ListView lvWeather;
    private ArrayAdapter<String> adapter;
    private ProgressDialog progressDialog;

    private String adress = "http://guolin.tech/api/china/";
    public static String weatherId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        btnBack = view.findViewById(R.id.btn_back);
        lvWeather = view.findViewById(R.id.lv_weather);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, dataList);
        lvWeather.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvWeather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectCity = cityList.get(position);
                    queryCounties();
                } else if (currentLevel == LEVEL_CONTY) {
                    weatherId = countyList.get(position).getWeatherId();
                    Intent intent = new Intent(getActivity(), WeatherActivity.class);
                    intent.putExtra(EXTRA_WEATHER_ID, weatherId);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_CONTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
        // ->查询全国省份
        queryProvinces();
    }

    /**
     * 查询省份
     */
    private void queryProvinces() {
        tvTitle.setText("中国");
        btnBack.setVisibility(View.GONE);
        // 从数据库中查询
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            lvWeather.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            queryFromServer(adress, LEVEL_PROVINCE);
        }
    }

    /**
     * 查询城市
     */
    private void queryCities() {
        tvTitle.setText(selectProvince.getProvinceName());
        btnBack.setVisibility(View.VISIBLE);
        // 从数据库中查询被选中省份里的所有城市
        cityList = DataSupport.where("provinceId = ?",
                String.valueOf(selectProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            lvWeather.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            queryFromServer(adress + selectProvince.getProvinceCode(), LEVEL_CITY);
        }
    }

    /**
     * 查询县/区
     */
    private void queryCounties() {
        tvTitle.setText(selectCity.getCityName());
        btnBack.setVisibility(View.VISIBLE);
        // 从数据库中查询被选中城市里的所有县/区
        countyList = DataSupport.where("cityId = ?",
                String.valueOf(selectCity.getId())).find(County.class);
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            lvWeather.setSelection(0);
            currentLevel = LEVEL_CONTY;
        } else {
            queryFromServer(adress + selectProvince.getProvinceCode() + "/"
                    + selectCity.getCityCode(), LEVEL_CONTY);
        }
    }

    private void queryFromServer(String url, final int level) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                boolean result = false;
                if (level == LEVEL_PROVINCE) {
                    result = JSONHandler.handlerProvinceResponse(responseData);
                } else if (level == LEVEL_CITY) {
                    result = JSONHandler.handlerCityResponse(responseData, selectProvince.getId());
                } else if (level == LEVEL_CONTY) {
                    result = JSONHandler.handlerCountyResponse(responseData, selectCity.getId());
                }
                if (result) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if (level == LEVEL_PROVINCE) {
                                queryProvinces();
                            } else if (level == LEVEL_CITY) {
                                queryCities();
                            } else if (level == LEVEL_CONTY) {
                                queryCounties();
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "从服务器获取数据失败!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    /**
     * 关闭加载框
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
