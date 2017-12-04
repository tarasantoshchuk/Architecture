package com.tarasantoshchuk.arch.core.core;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.core.routing.callback_impl.SafeRouterCallback;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;
import com.tarasantoshchuk.arch.util.Logger;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ArchitectureDelegate<
        V extends View,
        P extends Presenter,
        I extends Interactor,
        R extends Router>

        implements

        PresenterCallbacks<V, R, I>,
        ViewCallbacks<R>,
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
    public void unsubscribeOnDestory(Disposable disposable) {
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

    @Override
    public <T> Observer<T> stateObserver(Action<T> onNext) {
        return new AutoUnsubscribeObserver<>(onNext, mUnsubscribeOnStopSubscriptions);
    }

    final class AutoUnsubscribeObserver<T> implements Observer<T>, SingleObserver<T> {
        private final Action<T> mOnNext;
        private final CompositeDisposable mDisposablesCollector;

        AutoUnsubscribeObserver(Action<T> onNext, CompositeDisposable disposablesCollector) {
            mOnNext = onNext;
            mDisposablesCollector = disposablesCollector;
        }

        @Override
        @CallSuper
        public void onSubscribe(Disposable d) {
            Logger.v(this, "onSubscribe");
            mDisposablesCollector.add(d);
        }

        @Override
        public void onSuccess(T t) {
            Logger.v(this, "onSuccess, value " + t);
            mOnNext.apply(t);
        }

        @Override
        public void onNext(T t) {
            Logger.v(this, "onNext, value " + t);
            mOnNext.apply(t);
        }

        @Override
        public void onError(Throwable e) {
            Logger.e(this, "onError", e);
        }

        @Override
        public void onComplete() {
            Logger.v(this, "onComplete");
        }
    }
}
