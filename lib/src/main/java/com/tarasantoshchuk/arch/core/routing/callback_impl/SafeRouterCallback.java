package com.tarasantoshchuk.arch.core.routing.callback_impl;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;

import javax.inject.Provider;

public class SafeRouterCallback<V extends View> implements RouterCallback {
    private RouterCallback mInner;
    private Provider<CachedActions<V>> mViewActionsProvider;

    public SafeRouterCallback(RouterCallback inner, Provider<CachedActions<V>> provider) {
        mInner = inner;
        mViewActionsProvider = provider;
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Bundle extras) {
        RouterCallback.super.startScreen(screen, extras);
        submit(view -> mInner.startScreen(screen, extras));
    }

    @Override
    public void cancel(Bundle extras) {
        RouterCallback.super.cancel(extras);
        submit(view -> mInner.cancel(extras));
    }

    @Override
    public void success(Bundle extras) {
        RouterCallback.super.success(extras);
        submit(view -> mInner.success(extras));
    }

    @Override
    public Bundle startData() {
        return mInner.startData();
    }

    private void submit(Action<V> action) {
        mViewActionsProvider
                .get()
                .submit(action);
    }
}
