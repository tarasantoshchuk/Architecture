package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Fragment;
import android.content.Intent;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;

import static com.tarasantoshchuk.arch.core.routing.BundleConverter.fromAndroidBundle;
import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.requestCode;

public class FragmentRouterCallback extends ActivityRouterCallback {
    private Fragment mFragment;

    public FragmentRouterCallback(Fragment fragment) {
        super(fragment.getActivity());
        mFragment = fragment;
    }

    @Override
    protected void launchActivity(ScreensResolver.Screen screen, Intent intent) {
        mFragment.startActivityForResult(intent, requestCode(screen));
    }

    @Override
    public Bundle startData() {
        return fromAndroidBundle(mFragment.getArguments());
    }
}
