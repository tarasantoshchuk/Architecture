package com.tarasantoshchuk.arch.core.presenter;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.util.Logger;

public interface Presenter<V, R, I> {
    @CallSuper
    default void onViewAttached(V view) {
        Logger.v(this, "onViewAttached");
    }

    @CallSuper
    default void onCreate(PresenterCallbacks<V, R, I> callbacks) {
        Logger.v(this, "onCreate");
    }

    @CallSuper
    default void onDestroy() {
        Logger.v(this, "onDestroy");
    }
}
