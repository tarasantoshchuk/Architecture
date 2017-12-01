package com.tarasantoshchuk.arch.core.routing.callback_impl;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;


public abstract class BaseRouterCallback implements RouterCallback {
    @Override
    public void cancel() {
        cancel(Bundle.EMPTY);
    }

    @Override
    public void success() {
        success(Bundle.EMPTY);
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen) {
        startScreen(screen, Bundle.EMPTY);
    }
}
