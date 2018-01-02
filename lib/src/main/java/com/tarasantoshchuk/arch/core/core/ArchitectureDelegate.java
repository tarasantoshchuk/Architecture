package com.tarasantoshchuk.arch.core.core;


import android.content.Intent;

import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.routing.callback_impl.SafeRouterCallback;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;
import com.tarasantoshchuk.arch.util.log.Logger;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ArchitectureDelegate<
        V extends View,
        P extends Presenter,
        I extends Interactor,
        R extends Router>

        implements

        PresenterCallbacks<V, R, I>,
        ViewCallbacks,
        RouterCallbacks<P> {
    private RootArchitectureDelegate<?, P, I, R> mParent;

    private V mView;
    private P mPresenter;
    private R mRouter;

    private CachedActions<V> mViewActions = new CachedActions<>();

    private CompositeDisposable mUnsubscribeOnDestroySubscriptions = new CompositeDisposable();
    private CompositeDisposable mUnsubscribeOnStopSubscriptions = new CompositeDisposable();

    ArchitectureDelegate(RootArchitectureDelegate<?, P, I, R> parent, ScreenConfigurator<V, P, R> configurator) {
        mParent = parent;

        mView = configurator.view();
        mPresenter = configurator.presenter();
        mRouter = configurator.router();
    }

    public P presenter() {
        return mPresenter;
    }

    public R router() {
        return mRouter;
    }

    @Override
    public I interactor() {
        return mParent.interactor();
    }

    @Override
    public void applyOnView(Action<V> action) {
        mViewActions.submit(action);
    }

    @Override
    public void unsubscribeOnDetach(Disposable disposable) {
        mUnsubscribeOnStopSubscriptions.add(disposable);
    }

    @Override
    public void unsubscribeOnDestroy(Disposable disposable) {
        mUnsubscribeOnDestroySubscriptions.add(disposable);
    }

    @Override
    public RouterCallback routerImplementation() {
        return new SafeRouterCallback<>(mView.provideRouterImplementation(), () -> mViewActions);
    }

    void replaceView(V v) {
        mView = v;
        mView.setCallback(this);
    }

    @SuppressWarnings("unchecked")
    void onCreate() {
        Logger.v(this, "onCreate");

        mRouter.onCreate(this);
        mPresenter.onCreate(this);

        mView.setCallback(this);
    }

    @SuppressWarnings("unchecked")
    void onStart() {
        Logger.v(this, "onStart");

        mPresenter.onViewAttached(mView);
        mView.onAttachToPresenter(mPresenter);

        mViewActions.setReceiver(mView);
    }

    void onStop() {
        Logger.v(this, "onStop");

        mViewActions.removeReceiver();

        Logger.v(this, "onStop, mUnsubscribeOnStopSubscriptions.size() " + mUnsubscribeOnStopSubscriptions.size());

        mUnsubscribeOnStopSubscriptions.clear();
    }

    void onDestroy() {
        Logger.v(this, "onStop");
        mUnsubscribeOnDestroySubscriptions.clear();

        mPresenter.onDestroy();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyScreenResult(boolean isOk, ScreensResolver.Screen screen, Intent data) {
        router()
                .onScreenResult(isOk, screen, data);
    }
}
