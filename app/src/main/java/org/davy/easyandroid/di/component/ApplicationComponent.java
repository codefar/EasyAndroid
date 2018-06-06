package org.davy.easyandroid.di.component;

import android.app.Application;

import com.google.gson.Gson;

import org.davy.easyandroid.di.module.ApplicationModule;
import org.davy.easyandroid.di.module.DaoModule;
import org.davy.easyandroid.di.module.NetModule;
import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/10/24
 * time: 12:14
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class, DaoModule.class})
public interface ApplicationComponent {

    Application application();

    Gson gson();

//    DaoMaster daoMaster();
//
//    DaoSession daoSession();

    Database database();
}