package org.davy.easyandroid.base;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import org.davy.easyandroid.di.component.DaggerFragmentComponent;
import org.davy.easyandroid.di.component.FragmentComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseFragment extends Fragment {

    protected Logger mLogger = LoggerFactory.getLogger(BaseFragment.this.getClass());
    protected FragmentComponent mFragmentComponent;

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentComponent = DaggerFragmentComponent.builder().build();
        mFragmentComponent.inject(this);
    }
}
