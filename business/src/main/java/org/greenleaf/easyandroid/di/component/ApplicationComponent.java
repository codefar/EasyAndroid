package org.greenleaf.easyandroid.di.component;

import android.app.Application;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;

import org.greenleaf.easyandroid.api.GankIoService;
import org.greenleaf.easyandroid.api.NeteasApiService;
import org.greenleaf.easyandroid.api.ServerService;
import org.greenleaf.easyandroid.di.module.ApplicationModule;
import org.greenleaf.easyandroid.di.module.BusModule;
import org.greenleaf.easyandroid.di.module.DaoModule;
import org.greenleaf.easyandroid.di.module.NetModule;
import org.greenleaf.easyandroid.domain.DaoMaster;
import org.greenleaf.easyandroid.domain.DaoSession;
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
@Component(modules = {ApplicationModule.class, BusModule.class, NetModule.class, DaoModule.class})
public interface ApplicationComponent {

    Application application();

    Gson gson();

    DaoMaster daoMaster();

    DaoSession daoSession();

    Database database();

    ServerService service();

    LocalBroadcastManager localBroadcastManager();

    GankIoService gankIoService();

    NeteasApiService neteasApiService();
}