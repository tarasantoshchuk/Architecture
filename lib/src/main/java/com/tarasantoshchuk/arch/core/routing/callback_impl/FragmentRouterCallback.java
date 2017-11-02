package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Fragment;
import android.content.Intent;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.BundleConverter;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

import static com.tarasantoshchuk.arch.core.routing.BundleConverter.fromAndroidBundle;
import static com.tarasantoshchuk.arch.core.routing.BundleConverter.toAndroidBundle;
import static com.tarasantoshchuk.arch.core.routing.Routers.intentWithBundle;
import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.requestCode;
import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.screenClass;

public class FragmentRouterCallback extends ActivityRouterCallback {
    private Fragment mFragment;

    public FragmentRouterCallback(Fragment fragment) {
        super(fragment.getActivity());
        mFragment = fragment;
    }

    @Override
    public void startScreen(ScreensResolver.Screen screen, Bundle extras) {
        Intent intent = intentWithBundle(mFragment.getActivity(), screen, extras);
        mFragment.startActivityForResult(intent, requestCode(screen));
    }

    @Override
    public Bundle startData() {
        return fromAndroidBundle(mFragment.getArguments());
    }
}
