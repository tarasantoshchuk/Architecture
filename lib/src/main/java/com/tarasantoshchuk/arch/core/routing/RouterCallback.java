package com.tarasantoshchuk.arch.core.routing;

public interface RouterCallback {
    void startScreen(ScreensResolver.Screen screen, Bundle extras);

    void cancel(Bundle extras);

    void success(Bundle extras);

    Bundle startData();

    void cancel();

    void success();

    void startScreen(ScreensResolver.Screen screen);
}
