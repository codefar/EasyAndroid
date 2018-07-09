package org.greenleaf.easyandroid;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.greenleaf.easyandroid.core.utils.ThreadUtils;
import org.greenleaf.easyandroid.di.ComponentManager;
import org.greenleaf.utils.AssetsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2018/6/6
 * time: 18:09
 */

public class EasyApplication extends Application {

    public static EasyApplication sContext;
    private static Logger mLogger = LoggerFactory.getLogger(EasyApplication.class);

    @Override
    public void onCreate() {
//        StrickModeHelper.init();
        super.onCreate();
        sContext = this;
        ComponentManager.appComponent();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        ThreadUtils.getInstance().addSingleTask(new Runnable() {
            @Override
            public void run() {
                try {
                    AssetsUtils.copyAssetsFile(getApplicationContext(),  "ff.txt", getExternalFilesDir("").getAbsolutePath()
                            + File.separator + "ff.txt");
                } catch (IOException e) {
                    mLogger.info("copyAssetsFile {} err ", "ff.txt");
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
