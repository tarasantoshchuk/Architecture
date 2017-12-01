package com.tarasantoshchuk.arch.core.view.impl;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegates;
import com.tarasantoshchuk.arch.core.core.RootArchitectureDelegate;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.BundleConverter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.core.view.ViewId;
import com.tarasantoshchuk.arch.util.Action;

import javax.inject.Inject;
import javax.inject.Provider;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class BaseFragment<P extends Presenter> extends Fragment implements View<P> {
    /* views implementation */

    @Inject
    Provider<RootArchitectureDelegate> mArchitectureProvider;

    private ViewCallbacks<? extends Router> mViewCallbacks;

    @Override
    public final void setCallback(ViewCallbacks<? extends Router> callbacks) {
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

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewCallbacks
                .router()
                .onScreenResult(
                        resultCode == Activity.RESULT_OK,
                        ScreensResolver.screen(resultCode),
                        BundleConverter.fromIntent(data)
                );
    }

    public RootView getRootView() {
        return (RootView) getActivity();
    }

    protected final <T> void observeState(Observable<T> observable, Action<T> observer) {
        observable
                .subscribe(
                        stateObserver(observer)
                );
    }

    private <T> Observer<T> stateObserver(Action<T> onNext) {
        return mViewCallbacks.stateObserver(onNext);
    }

    @Override
    public ViewId viewId() {
        return new ViewId(getClass().getSimpleName());
    }
}
