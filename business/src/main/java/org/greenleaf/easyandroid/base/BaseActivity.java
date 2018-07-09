package org.greenleaf.easyandroid.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import org.greenleaf.easyandroid.di.ComponentManager;
import org.greenleaf.easyandroid.di.component.ActivityComponent;
import org.greenleaf.easyandroid.di.component.DaggerActivityComponent;
import org.greenleaf.easyandroid.di.module.ActivityModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseActivity extends AppCompatActivity {

    protected Logger mLogger = LoggerFactory.getLogger(BaseActivity.this.getClass());
    protected ActivityComponent mActivityComponent;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder().applicationComponent(ComponentManager.appComponent())
                .activityModule(new ActivityModule(this)).build();
        mActivityComponent.inject(this);
        initComponent();
    }

    protected void initComponent() {
    }
}
