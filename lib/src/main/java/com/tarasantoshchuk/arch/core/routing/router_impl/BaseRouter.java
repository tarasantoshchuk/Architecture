package com.tarasantoshchuk.arch.core.routing.router_impl;


import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.tarasantoshchuk.arch.core.RouterCallbacks;
import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;
import com.tarasantoshchuk.arch.util.Logger;

public class BaseRouter<P, S extends Screen> implements Router<P, S> {
    private RouterCallbacks<P> mCallbacks;

    @Override
    public final void onCreate(RouterCallbacks<P> callbacks) {
        Logger.v(this, "onCreate");

        mCallbacks = callbacks;
    }

    @CallSuper
    @Override
    public void onScreenResult(boolean isOk, @NonNull S screen, @NonNull Bundle data) {
        Logger.v(this, "onScreenResult, isOk " + isOk + ", screen " + screen + ", data " + data);
    }

    @Override
    public final void back() {
        Logger.v(this, "back");

        callback().cancel();
    }

    @Override
    public final void finish() {
        Logger.v(this, "finish");

        callback().success();
    }

    protected final P presenter() {
        return mCallbacks.presenter();
    }

    protected final RouterCallback callback() {
        return mCallbacks.routerImplementation();
    }
}
