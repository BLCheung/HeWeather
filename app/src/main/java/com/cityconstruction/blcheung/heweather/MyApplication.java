package com.cityconstruction.blcheung.heweather;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by BLCheung.
 * Date:2018/3/25 17:02
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
