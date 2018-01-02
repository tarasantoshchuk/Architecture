package com.tarasantoshchuk.arch.core.routing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.util.log.Logger;

public interface RouterCallback {
    @CallSuper
    default void startScreen(ScreensResolver.Screen screen, Intent data) {
        Logger.v(this, "startScreen, screen " + screen + ", data " + data);
    }

    default void startScreen(ScreensResolver.Screen screen, Bundle bundle) {
        startScreen(screen, new Intent().putExtras(bundle));
    }

    default void startScreen(ScreensResolver.Screen screen) {
        startScreen(screen, Bundle.EMPTY);
    }

    @CallSuper
    default void cancel(Intent data) {
        Logger.v(this, "cancel, data " + data);
    }

    default void cancel(Bundle extras) {
        cancel(new Intent().putExtras(extras));
    }

    default void cancel() {
        cancel(Bundle.EMPTY);
    }

    default void success(Intent data) {
        Logger.v(this, "success, data " + data);
    }

    default void success(Bundle extras) {
        success(new Intent().putExtras(extras));
    }

    default void success() {
        success(Bundle.EMPTY);
    }

    Intent startIntent();

    default Bundle startData() {
        return startIntent().getExtras();
    }
}
