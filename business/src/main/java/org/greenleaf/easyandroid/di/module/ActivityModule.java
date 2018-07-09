package org.greenleaf.easyandroid.di.module;

import android.app.Activity;

import org.greenleaf.easyandroid.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

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

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return this.mActivity;
    }

}
