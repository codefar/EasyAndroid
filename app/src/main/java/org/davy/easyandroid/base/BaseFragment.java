package org.davy.easyandroid.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.davy.easyandroid.di.component.DaggerFragmentComponent;
import org.davy.easyandroid.di.component.FragmentComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.yokeyword.swipebackfragment.SwipeBackFragment;
import me.yokeyword.swipebackfragment.SwipeBackLayout;

public abstract class BaseFragment extends SwipeBackFragment {

    protected Logger mLogger = LoggerFactory.getLogger(BaseFragment.this.getClass());
    protected FragmentComponent mFragmentComponent;

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentComponent = DaggerFragmentComponent.builder().build();
        mFragmentComponent.inject(this);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        SwipeBackLayout swipeBackLayout = (SwipeBackLayout) attachToSwipeBack(view);
        swipeBackLayout.setBackground(null);
        swipeBackLayout.setShadow(new ColorDrawable(Color.BLACK), SwipeBackLayout.EDGE_LEFT);
        return swipeBackLayout;
    }

    protected abstract int getLayoutRes();

    @SuppressWarnings("TypeParameterUnusedInFormals")
    public <T extends View> T findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }
}
