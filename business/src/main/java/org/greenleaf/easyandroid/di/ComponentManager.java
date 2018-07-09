package org.greenleaf.easyandroid.di;

import org.greenleaf.easyandroid.EasyApplication;
import org.greenleaf.easyandroid.di.component.ApplicationComponent;
import org.greenleaf.easyandroid.di.component.DaggerApplicationComponent;
import org.greenleaf.easyandroid.di.module.ApplicationModule;
import org.greenleaf.easyandroid.di.module.BusModule;
import org.greenleaf.easyandroid.di.module.DaoModule;
import org.greenleaf.easyandroid.di.module.NetModule;

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
                            .applicationModule(new ApplicationModule(EasyApplication.sContext))
                            .netModule(new NetModule())
                            .daoModule(new DaoModule(EasyApplication.sContext))
                            .busModule(new BusModule(EasyApplication.sContext))
                            .build();
                }
            }
        }
        return sApplicationComponent;
    }
}
