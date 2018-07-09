package org.greenleaf.easyandroid.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import org.greenleaf.easyandroid.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
