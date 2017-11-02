package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Activity;
import android.view.View;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

public class AndroidViewRouterCallback extends ActivityRouterCallback {
    public AndroidViewRouterCallback(View view) {
        super((Activity) view.getContext());
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Bundle bundle) {
        throw new UnsupportedOperationException();
    }
}
