package com.tarasantoshchuk.arch.core.routing.router_impl;


import com.tarasantoshchuk.arch.core.RouterCallbacks;
import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;

public class BaseRouter<P, S extends Screen> implements Router<P, S> {
    private RouterCallbacks<P> mCallbacks;

    @Override
    public final void onCreate(RouterCallbacks<P> callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    public void onScreenResult(boolean isOk, S screen, Bundle data) {

    }

    @Override
    public void back() {
        callback().cancel();
    }

    @Override
    public void finish() {
        callback().success();
    }

    protected final P presenter() {
        return mCallbacks.presenter();
    }

    protected final RouterCallback callback() {
        return mCallbacks.routerImplementation();
    }
}
