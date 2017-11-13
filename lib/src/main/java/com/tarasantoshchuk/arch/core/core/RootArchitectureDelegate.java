package com.tarasantoshchuk.arch.core.core;


import android.support.annotation.NonNull;

import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.callback_impl.SafeRouterCallback;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Logger;

import java.util.HashMap;

public final class RootArchitectureDelegate<
        V extends RootView,
        P extends Presenter,
        I extends Interactor,
        R extends Router>

    extends

        ArchitectureDelegate<V, P, I, R>

{
    private I mInteractor;

    private HashMap<String, ArchitectureDelegate> mSubDelegates = new HashMap<>();

    RootArchitectureDelegate(RootScreenConfigurator<V, P, I, R> configurator) {
        super(null, configurator);

        mInteractor = configurator.interactor();
    }

    public I interactor() {
        return mInteractor;
    }

    public RouterCallback routerImplementation() {
        Logger.v(this, "routerImplementation");

        return new SafeRouterCallback(mView.provideRouterImplementation(), this);
    }

    @Override
    void onCreate() {
        super.onCreate();

        mInteractor.onCreate();
    }

    @SuppressWarnings("unchecked")
    void replaceView(V rootView, View view) {
        super.replaceView(rootView);

        subDelegate(view).replaceView(view);
    }

    @NonNull
    private ArchitectureDelegate subDelegate(View view) {
        String viewKey = view.tag();
        if (!mSubDelegates.containsKey(viewKey)) {

        }

        return mSubDelegates.get(viewKey);
    }

    @Override
    void onDestroy() {
        super.onDestroy();

        mInteractor.onDestroy();

        for(ArchitectureDelegate subDelegate: mSubDelegates.values()) {
            subDelegate.onDestroy();
        }
    }

    void onCreate(View view) {
        subDelegate(view).onCreate();
    }

    void onStart(View view) {
        subDelegate(view).onStart();
    }

    void onStop(View view) {
        subDelegate(view).onStop();
    }
}
