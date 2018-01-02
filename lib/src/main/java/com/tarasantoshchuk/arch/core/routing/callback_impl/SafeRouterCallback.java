package com.tarasantoshchuk.arch.core.routing.callback_impl;

import android.content.Intent;
import android.os.Bundle;

import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;
import com.tarasantoshchuk.arch.util.Provider;


public class SafeRouterCallback<V extends View> implements RouterCallback {
    private RouterCallback mInner;
    private Provider<CachedActions<V>> mViewActionsProvider;

    /**
     * View absence should not delay retrieving of {@link #startData()}
     */
    private Bundle mStartData;

    private Intent mStartIntent;

    public SafeRouterCallback(RouterCallback inner, Provider<CachedActions<V>> provider) {
        mInner = inner;
        mViewActionsProvider = provider;

        mStartData = inner.startData();
        mStartIntent = inner.startIntent();
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Intent data) {
        RouterCallback.super.startScreen(screen, data);
        submit(view -> mInner.startScreen(screen, data));
    }

    @Override
    public void cancel(Intent data) {
        RouterCallback.super.cancel(data);
        submit(view -> mInner.cancel(data));
    }

    @Override
    public void success(Intent data) {
        RouterCallback.super.success(data);
        submit(view -> mInner.success(data));
    }

    @Override
    public Bundle startData() {
        return mStartData;
    }

    @Override
    public Intent startIntent() {
        return mStartIntent;
    }

    private void submit(Action<V> action) {
        mViewActionsProvider
                .get()
                .submit(action);
    }
}
