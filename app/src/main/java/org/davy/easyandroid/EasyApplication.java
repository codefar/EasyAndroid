package org.davy.easyandroid;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.davy.easyandroid.di.ComponentManager;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2018/6/6
 * time: 18:09
 */

public class EasyApplication extends Application {

    public static EasyApplication sAppContext;

    @Override
    public void onCreate() {
//        StrickModeHelper.init();
        super.onCreate();
        sAppContext = this;
        ComponentManager.appComponent();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
