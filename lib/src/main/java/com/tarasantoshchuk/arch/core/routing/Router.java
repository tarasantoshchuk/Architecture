package com.tarasantoshchuk.arch.core.routing;


import com.tarasantoshchuk.arch.core.RouterCallbacks;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;

public interface Router<P, S extends Screen> {
    void onCreate(RouterCallbacks<P> callbacks);
    void onScreenResult(boolean isOk, S screen, Bundle data);

    void back();
    void finish();
}
