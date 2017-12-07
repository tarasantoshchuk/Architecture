package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

import io.reactivex.disposables.Disposable;

public interface ViewCallbacks{
    void notifyScreenResult(boolean isOk, ScreensResolver.Screen screen, Bundle bundle);

    void unsubscribeOnDetach(Disposable d);
}
