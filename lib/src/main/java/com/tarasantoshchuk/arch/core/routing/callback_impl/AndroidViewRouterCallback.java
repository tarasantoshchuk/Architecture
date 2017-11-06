package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

public class AndroidViewRouterCallback extends ActivityRouterCallback {
    public AndroidViewRouterCallback(View view) {
        super((Activity) view.getContext());
    }

    @Override
    protected void launchActivity(ScreensResolver.Screen screen, Intent intent) {
        throw new UnsupportedOperationException();
    }
}
