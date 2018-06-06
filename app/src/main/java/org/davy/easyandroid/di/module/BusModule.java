package org.davy.easyandroid.di.module;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/10/24
 * time: 12:14
 */
@Module
public class BusModule {

    @Singleton
    @Provides
    LocalBroadcastManager providerLocalBroadcastManager(@NonNull Context context) {
        return LocalBroadcastManager.getInstance(context);
    }
}
