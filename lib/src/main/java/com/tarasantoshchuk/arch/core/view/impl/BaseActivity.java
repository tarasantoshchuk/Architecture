package com.tarasantoshchuk.arch.core.view.impl;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.routing.BundleConverter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.ViewId;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.Logger;

import io.reactivex.Observable;
import io.reactivex.Observer;


public abstract class BaseActivity<P> extends AppCompatActivity implements RootView<P> {
    /* views implementation */

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

        ArchitectureDelegates.onCreateView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArchitectureDelegates.onStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        ArchitectureDelegates.onStop(this);
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

    @Override
    public ViewId viewId() {
        return new ViewId(getClass().getSimpleName());
    }
}
