package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

import static com.tarasantoshchuk.arch.core.routing.BundleConverter.fromAndroidBundle;
import static com.tarasantoshchuk.arch.core.routing.Routers.intentWithBundle;
import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.requestCode;

public class SupportFragmentRouterCallback extends ActivityRouterCallback {
    private Fragment mFragment;

    public SupportFragmentRouterCallback(Fragment fragment) {
        super(fragment.getActivity());
        mFragment = fragment;
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Bundle bundle) {
        Intent intent = intentWithBundle(mFragment.getActivity(), screen, bundle);
        mFragment.startActivityForResult(intent, requestCode(screen));
    }

    @Override
    public Bundle startData() {
        return fromAndroidBundle(mFragment.getArguments());
    }
}
