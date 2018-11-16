package com.xiaojiu.studylibs;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import com.awen.photo.FrescoImageLoader;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class MyApp extends Application {

    private static MyApp mApplication;
    private DisplayMetrics displayMetrics;

    @Override
    public void onCreate() {
        super.onCreate();
        FrescoImageLoader.init(this);
        mApplication = this;
        initToast();
        displayMetrics = getResources().getDisplayMetrics();
       /* displayMetrics.density = 4;
        displayMetrics.scaledDensity = 4;*/
        initDisplayMetrics();
    }

    private void initDisplayMetrics() {
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        int heightPixels = displayMetrics.heightPixels;
        float scaledDensity = displayMetrics.scaledDensity;
        int widthPixels = displayMetrics.widthPixels;
        float xdpi = displayMetrics.xdpi;
        float ydpi = displayMetrics.ydpi;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer
                .append("density:" + density)
                .append("===densityDpi:" + densityDpi)
                .append("===heightPixels:" + heightPixels)
                .append("===scaledDensity:" + scaledDensity)
                .append("===widthPixels:" + widthPixels)
                .append("===xdpi:" + xdpi)
                .append("===ydpi:" + ydpi);
        Log.e("TAG", stringBuffer.toString());
    }

    private void initToast() {


    }

    public static Context getInstance() {
        return mApplication;
    }

}
