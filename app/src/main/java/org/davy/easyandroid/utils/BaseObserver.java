package org.davy.easyandroid.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.davy.easyandroid.bean.ApiResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<ApiResponse<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(ApiResponse<T> value) {
        if (value.isError()) {
            onHandleSuccess(value.getResults());
        } else {
            onHandleError(value.toString());
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}