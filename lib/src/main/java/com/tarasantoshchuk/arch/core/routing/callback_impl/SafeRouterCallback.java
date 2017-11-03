package com.tarasantoshchuk.arch.core.routing.callback_impl;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;

import javax.inject.Provider;

public class SafeRouterCallback implements RouterCallback {
    private RouterCallback mInner;
    private Provider<CachedActions> mViewActionsProvider;

    public SafeRouterCallback(RouterCallback inner, Provider<CachedActions> provider) {
        mInner = inner;
        mViewActionsProvider = provider;
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Bundle extras) {
        submit(view -> mInner.startScreen(screen, extras));
    }

    @Override
    public void cancel(Bundle extras) {
        submit(view -> mInner.cancel(extras));
    }

    @Override
    public void success(Bundle extras) {
        submit(view -> mInner.success(extras));
    }

    @Override
    public Bundle startData() {
        return mInner.startData();
    }

    @SuppressWarnings("unchecked")
    private void submit(Action action) {
        mViewActionsProvider
                .get()
                .submit(action);
    }
}
