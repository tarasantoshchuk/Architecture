package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Activity;
import android.content.Intent;

import com.tarasantoshchuk.arch.core.routing.Bundle;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;

import static com.tarasantoshchuk.arch.core.routing.BundleConverter.fromIntent;
import static com.tarasantoshchuk.arch.core.routing.Routers.intentFromBundle;
import static com.tarasantoshchuk.arch.core.routing.Routers.intentWithBundle;
import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.requestCode;

public class ActivityRouterCallback extends BaseRouterCallback {
    private Activity mActivity;

    public ActivityRouterCallback(Activity activity) {
        mActivity = activity;
    }

    @Override
    public final void startScreen(Screen screen, Bundle bundle) {
        Intent intent = intentWithBundle(mActivity, screen, bundle);
        launchActivity(screen, intent);
    }

    protected void launchActivity(Screen screen, Intent intent) {
        mActivity.startActivityForResult(intent, requestCode(screen));
    }

    @Override
    public final void cancel(Bundle bundle) {
        mActivity.setResult(Activity.RESULT_CANCELED, intentFromBundle(bundle));
        mActivity.finish();
    }

    @Override
    public final void success(Bundle bundle) {
        mActivity.setResult(Activity.RESULT_OK, intentFromBundle(bundle));
        mActivity.finish();
    }

    @Override
    public Bundle startData() {
        return fromIntent(mActivity.getIntent());
    }
}
