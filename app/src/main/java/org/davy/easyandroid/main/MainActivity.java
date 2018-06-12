package org.davy.easyandroid.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.davy.easyandroid.R;
import org.davy.easyandroid.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("main");
        if (fragment == null) {
            fragment = MainFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().add(R.id.contentPanel, fragment, "main")
                .addToBackStack(null).commit();
    }
}
