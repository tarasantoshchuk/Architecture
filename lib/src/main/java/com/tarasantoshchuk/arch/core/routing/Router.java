package com.tarasantoshchuk.arch.core.routing;


import android.support.annotation.NonNull;

import com.tarasantoshchuk.arch.core.RouterCallbacks;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;

public interface Router<P, S extends Screen> {
    void onCreate(RouterCallbacks<P> callbacks);
    void onScreenResult(boolean isOk, @NonNull S screen, @NonNull Bundle data);

    void back();
    void finish();
}
