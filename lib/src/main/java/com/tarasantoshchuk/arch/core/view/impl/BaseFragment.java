package com.tarasantoshchuk.arch.core.view.impl;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.core.RootArchitectureDelegate;
import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.BundleConverter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;
import com.tarasantoshchuk.arch.core.routing.Routers;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.view.View;

import javax.inject.Inject;
import javax.inject.Provider;

public abstract class BaseFragment<P extends Presenter> extends Fragment implements View<P>, RouterCallbackProvider {
    /* views implementation */

    @Inject
    Provider<RootArchitectureDelegate> mArchitectureProvider;

    private ViewCallbacks<? extends Router> mViewCallbacks;

    @Override
    public final ArchitectureDelegateHolder architectureHolder() {
        return ViewModelProviders
                .of(this)
                .get(ArchitectureDelegateHolder.class);
    }

    @Override
    public final void setCallback(ViewCallbacks<? extends Router> callbacks) {
        mViewCallbacks = callbacks;
    }

    /* architecture delegation */

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RootArchitectureDelegate.onCreateView(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        RootArchitectureDelegate.onStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        RootArchitectureDelegate.onStop(this);
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
}
