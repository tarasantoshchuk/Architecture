package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.core.view.ViewId;
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

    private HashMap<ViewId, ArchitectureDelegate> mSubDelegates = new HashMap<>();

    RootArchitectureDelegate(RootScreenConfigurator<V, P, I, R> configurator) {
        super(null, configurator);

        mInteractor = configurator.interactor();
    }

    public I interactor() {
        return mInteractor;
    }

    @Override
    void onCreate() {
        super.onCreate();

        mInteractor.onCreate();
    }

    @SuppressWarnings("unchecked")
    void replaceView(V rootView, View view) {
        Logger.v(this, "replaceView >>>");
        replaceView(rootView);

        onSubViewCreate(view);
        Logger.v(this, "replaceView <<<");
    }

    @SuppressWarnings("unchecked")
    private ArchitectureDelegate subDelegate(View view) {
        Logger.v(this, "subDelegate >>>, viewId: " + view.viewId());
        return mSubDelegates.get(view.viewId());
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
        onSubViewCreate(view);
    }

    private void onSubViewCreate(View view) {
        Logger.v(this, "onSubViewCreate >>>");
        ArchitectureDelegate subDelegate = subDelegate(view);

        if (subDelegate == null) {
            Logger.v(this, "subDelegate == null");
            subDelegate = new ArchitectureDelegate(this, view.screenConfigurator());
            subDelegate.onCreate();
            mSubDelegates.put(view.viewId(), subDelegate);
        } else {
            Logger.v(this, "subDelegate != null");
            subDelegate.replaceView(view);
        }
        Logger.v(this, "onSubViewCreate <<<");
    }

    void onStart(View view) {
        Logger.v(this, "onStart >>>");
        subDelegate(view).onStart();
        Logger.v(this, "onStart <<<");
    }

    void onStop(View view) {
        subDelegate(view).onStop();
    }
}
