package com.example.zyfx_.mystudy;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;

/**
 * Created by zyfx_ on 2017/6/20.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        EasyHttp.init(this);
        TypefaceProvider.registerDefaultIconSets();

        EasyHttp.getInstance()
                .setBaseUrl("http://pre.im/app/35b7e63e8bed2fda358ea507644cb63f/")
                .setReadTimeOut(15 * 1000)
                .setWriteTimeOut(15 * 1000)
                .setConnectTimeout(15 * 1000)
                .setRetryCount(0)
                .setCacheMode(CacheMode.NO_CACHE);
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

}
