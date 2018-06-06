package org.davy.easyandroid.di.module;

import android.app.Activity;

import dagger.Module;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/10/24
 * time: 12:14
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
}
