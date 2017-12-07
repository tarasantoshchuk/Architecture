package com.tarasantoshchuk.arch.sample.utils;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.util.log.Logger;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class SimpleSingleObserver<T> implements SingleObserver<T> {
    @Override
    @CallSuper
    public void onSubscribe(Disposable d) {
        Logger.v(this, "onSubscribe");
    }

    @Override
    @CallSuper
    public void onSuccess(T t) {
        Logger.v(this, "onSuccess, value " + t);
    }

    @Override
    @CallSuper
    public void onError(Throwable e) {
        Logger.e(this, "onError", e);
    }
}
