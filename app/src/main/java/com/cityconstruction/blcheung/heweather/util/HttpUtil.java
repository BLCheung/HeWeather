package com.cityconstruction.blcheung.heweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by BLCheung.
 * Date:2018/3/25 17:21
 */

public class HttpUtil {

    /**
     * OkHttp发送网络请求
     *
     * @param url      服务器地址
     * @param callback 回调
     */
    public static void sendOkHttpRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
}
