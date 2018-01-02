package com.tarasantoshchuk.arch.core.routing.callback_impl;


import android.app.Activity;
import android.content.Intent;

import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;

import static com.tarasantoshchuk.arch.core.routing.ScreensResolver.requestCode;

public class ActivityRouterCallback implements RouterCallback {
    private Activity mActivity;

    public ActivityRouterCallback(Activity activity) {
        mActivity = activity;
    }

    @Override
    public final void startScreen(Screen screen, Intent data) {
        RouterCallback.super.startScreen(screen, data);
        data.setClass(mActivity, screen.getClass());
        launchActivity(screen, data);
    }

    protected void launchActivity(Screen screen, Intent intent) {
        mActivity.startActivityForResult(intent, requestCode(screen));
    }

    @Override
    public final void cancel(Intent data) {
        RouterCallback.super.cancel(data);
        mActivity.setResult(Activity.RESULT_CANCELED, data);
        mActivity.finish();
    }

    @Override
    public final void success(Intent data) {
        RouterCallback.super.success(data);
        mActivity.setResult(Activity.RESULT_OK, data);
        mActivity.finish();
    }

    @Override
    public Intent startIntent() {
        return mActivity.getIntent();
    }
}
