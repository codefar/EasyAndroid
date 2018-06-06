package org.davy.easyandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.davy.easyandroid.api.ServerService;
import org.davy.easyandroid.di.ComponentManager;
import org.davy.easyandroid.di.component.ActivityComponent;
import org.davy.easyandroid.di.component.DaggerActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    ActivityComponent mActivityComponent;

    @Inject
    ServerService mServerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityComponent = DaggerActivityComponent.builder().applicationComponent(ComponentManager.appComponent()).build();
        mActivityComponent.inject(this);

        mServerService.hashCode();
    }
}
