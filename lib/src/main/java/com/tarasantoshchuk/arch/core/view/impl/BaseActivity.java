package com.tarasantoshchuk.arch.core.view.impl;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.util.log.Logger;

import io.reactivex.Observable;


public abstract class BaseActivity<P> extends AppCompatActivity implements RootView<P> {
    /* views implementation */

    ViewCallbacks mViewCallbacks;

    @Override
    public final ArchitectureDelegateHolder architectureHolder() {
        return ViewModelProviders
                .of(this)
                .get(ArchitectureDelegateHolder.class);
    }

    @Override
    public final void setCallback(ViewCallbacks callbacks) {
        Logger.v(this, "setCallback");

        mViewCallbacks = callbacks;
    }

    /* delegation to architecture delegate */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.v(this, "onCreate");

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

    protected final <T> Observable<T> stateObservable(Observable<T> observable) {
        return observable
                .doOnSubscribe(mViewCallbacks::unsubscribeOnDetach);
    }

    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewCallbacks
                .notifyScreenResult(
                        Activity.RESULT_OK == resultCode,
                        ScreensResolver.screen(requestCode),
                        data
                );
    }
}
