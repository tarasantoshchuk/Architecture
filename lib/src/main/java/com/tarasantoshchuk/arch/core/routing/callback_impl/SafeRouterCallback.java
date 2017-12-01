package com.tarasantoshchuk.arch.core.routing.callback_impl;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;

import javax.inject.Provider;

public class SafeRouterCallback extends BaseRouterCallback {
    private RouterCallback mInner;
    private Provider<CachedActions> mViewActionsProvider;

    public SafeRouterCallback(RouterCallback inner, Provider<CachedActions> provider) {
        mInner = inner;
        mViewActionsProvider = provider;
    }

    @Override
    public void startScreen(final ScreensResolver.Screen screen, final Bundle extras) {
        submit(new Action() {
            @Override
            public void apply(Object view) {
                mInner.startScreen(screen, extras);
            }
        });
    }

    @Override
    public void cancel(final Bundle extras) {
        submit(new Action() {
            @Override
            public void apply(Object view) {
                mInner.cancel(extras);
            }
        });
    }

    @Override
    public void success(final Bundle extras) {
        submit(new Action() {
            @Override
            public void apply(Object view) {
                mInner.success(extras);
            }
        });
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
