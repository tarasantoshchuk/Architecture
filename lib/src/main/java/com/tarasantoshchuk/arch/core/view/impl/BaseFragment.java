package com.tarasantoshchuk.arch.core.view.impl;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;

import io.reactivex.Observable;

public abstract class BaseFragment<P extends Presenter> extends Fragment implements View<P> {
    /* views implementation */
    private ViewCallbacks mViewCallbacks;

    @Override
    public final void setCallback(ViewCallbacks callbacks) {
        mViewCallbacks = callbacks;
    }

    /* architecture delegation */

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArchitectureDelegates.onCreateView(getRootView(), this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        ArchitectureDelegates.onStart(getRootView(), this);
    }

    @Override
    public void onStop() {
        super.onStop();
        ArchitectureDelegates.onStop(getRootView(), this);
    }

    /* router */

    @Override
    public RouterCallback provideRouterImplementation() {
        return Routers.fromSupportFragment(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewCallbacks
                .notifyScreenResult(
                        resultCode == Activity.RESULT_OK,
                        ScreensResolver.screen(requestCode),
                        data
                );
    }

    private RootView getRootView() {
        return (RootView) getActivity();
    }

    protected <T> Observable<T> stateObservable(Observable<T> observable) {
        return observable
                .doOnSubscribe(mViewCallbacks::unsubscribeOnDetach);
    }
}
