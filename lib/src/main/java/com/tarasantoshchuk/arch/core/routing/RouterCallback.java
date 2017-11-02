package com.tarasantoshchuk.arch.core.routing;

public interface RouterCallback {
    void startScreen(ScreensResolver.Screen screen, Bundle extras);
    void cancel(Bundle extras);
    void success(Bundle extras);
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
