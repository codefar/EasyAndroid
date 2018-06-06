package org.davy.easyandroid.di;

import org.davy.easyandroid.EasyApplication;
import org.davy.easyandroid.di.component.ApplicationComponent;
import org.davy.easyandroid.di.module.ApplicationModule;

import davy.codes.App;
import davy.codes.component.NetModule;

/**
 * author: wangyonghua
 * version: V1.0
 * date: 2017/8/25
 * time: 16:21
 */

public class ComponentManager {
    private static ApplicationComponent sApplicationComponent;

    private ComponentManager() {}

    public static ApplicationComponent appComponent() {
        if (sApplicationComponent == null) {
            synchronized (ComponentManager.class) {
                if (sApplicationComponent == null) {
                    sApplicationComponent = DaggerApplicationComponent.builder()
                            .appContextModule(new ApplicationModule(EasyApplication.sAppContext))
                            .netModule(new NetModule())
                            .build();
                }
            }
        }
        return sApplicationComponent;
    }
}
