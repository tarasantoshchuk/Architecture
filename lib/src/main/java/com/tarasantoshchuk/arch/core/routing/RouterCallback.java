package com.tarasantoshchuk.arch.core.routing;

import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.util.log.Logger;

public interface RouterCallback {
    @CallSuper
    default void startScreen(ScreensResolver.Screen screen, Bundle extras) {
        Logger.v(this, "startScreen " + screen);
    }

    @CallSuper
    default void cancel(Bundle extras) {
        Logger.v(this, "cancel");
    }

    @CallSuper
    default void success(Bundle extras) {
        Logger.v(this, "success");
    }

    Bundle startData();

    default void cancel() {
        cancel(Bundle.EMPTY);
    }

    default void success() {
        success(Bundle.EMPTY);
    }

    default void startScreen(ScreensResolver.Screen screen) {
        startScreen(screen, Bundle.EMPTY);
    }
}
