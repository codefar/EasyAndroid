package org.davy.easyandroid.di.component;

import org.davy.easyandroid.base.BaseFragment;
import org.davy.easyandroid.di.module.FragmentModule;
import org.davy.easyandroid.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(modules = {FragmentModule.class}, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(BaseFragment fragment);
}
