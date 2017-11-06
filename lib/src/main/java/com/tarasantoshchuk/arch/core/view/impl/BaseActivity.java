package com.tarasantoshchuk.arch.core.view.impl;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tarasantoshchuk.arch.core.ArchitectureDelegate;
import com.tarasantoshchuk.arch.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.routing.BundleConverter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;


public abstract class BaseActivity<P> extends AppCompatActivity implements View<P>, RouterCallbackProvider {
    /* view implementation */

    ViewCallbacks<? extends Router> mViewCallbacks;

    @Override
    public final ArchitectureDelegateHolder architectureHolder() {
        return ViewModelProviders
                .of(this)
                .get(ArchitectureDelegateHolder.class);
    }

    @Override
    public final void setCallback(ViewCallbacks<? extends Router> callbacks) {
        Logger.v(this, "setCallback");

        mViewCallbacks = callbacks;
    }

    private <T> Observer<T> stateObserver(Action<T> onNext) {
        return mViewCallbacks.stateObserver(onNext);
    }

    /* delegation to architecture delegate */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("Activity", "onCreate");

        ArchitectureDelegate.onCreateView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArchitectureDelegate.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        ArchitectureDelegate.onStop(this);
    }

    /* router implementation provider */

    @Override
    public final RouterCallback provideRouterImplementation() {
        return Routers.fromActivity(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewCallbacks
                .router()
                .onScreenResult(
                        Activity.RESULT_OK == resultCode,
                        ScreensResolver.screen(requestCode),
                        BundleConverter.fromIntent(data)
                );
    }

    /* helper methods */

    protected final <T> void observeState(Observable<T> observable, Action<T> observer) {
        observable
                .subscribe(
                        stateObserver(observer)
                );
    }
}
