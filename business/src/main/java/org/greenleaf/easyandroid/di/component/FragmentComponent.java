package org.greenleaf.easyandroid.di.component;

import org.greenleaf.easyandroid.di.module.FragmentModule;
import org.greenleaf.easyandroid.di.scope.PerFragment;
import org.greenleaf.easyandroid.main.MainFragment;

import dagger.Component;

@PerFragment
@Component(modules = {FragmentModule.class}, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(MainFragment fragment);
}
