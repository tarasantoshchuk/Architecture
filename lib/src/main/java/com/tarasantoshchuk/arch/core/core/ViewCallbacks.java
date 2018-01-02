package com.tarasantoshchuk.arch.core.core;


import android.content.Intent;

import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

import io.reactivex.disposables.Disposable;

public interface ViewCallbacks{
    void notifyScreenResult(boolean isOk, ScreensResolver.Screen screen, Intent intent);

    void unsubscribeOnDetach(Disposable d);
}
