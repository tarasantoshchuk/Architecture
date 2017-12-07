package com.tarasantoshchuk.arch.sample.utils;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.util.log.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SimpleObserver<T> implements Observer<T> {
    @Override
    @CallSuper
    public void onSubscribe(Disposable d) {
        Logger.v(this, "onSubscribe");
    }

    @Override
    @CallSuper
    public void onNext(T t) {
        Logger.v(this, "onNext, value " + t);
    }

    @Override
    @CallSuper
    public void onError(Throwable e) {
        Logger.e(this, "onError", e);
    }

    @Override
    @CallSuper
    public void onComplete() {
        Logger.v(this, "onComplete");
    }
}
