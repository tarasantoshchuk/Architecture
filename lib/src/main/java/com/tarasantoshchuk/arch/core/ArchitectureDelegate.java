package com.tarasantoshchuk.arch.core;


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

import javax.inject.Provider;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public final class ArchitectureDelegate<
        V extends View,
        P extends Presenter,
        I extends Interactor,
        R extends Router>

    implements

        PresenterCallbacks<V, R, I>,
        ViewCallbacks<R>,
        RouterCallbacks<P>,
        InteractorCallbacks<P>,
        Provider<CachedActions> {

    private V mView;
    private P mPresenter;
    private I mInteractor;
    private R mRouter;

    private CachedActions<V> mViewActions = new CachedActions<>();
    private CompositeDisposable mResources = new CompositeDisposable();

    private ArchitectureDelegate(ScreenConfigurator<V, P, I, R> configurator) {
        mView = configurator.view();
        mPresenter = configurator.presenter();
        mInteractor = configurator.interactor();
        mRouter = configurator.router();
    }

    public P presenter() {
        return mPresenter;
    }

    public I interactor() {
        return mInteractor;
    }

    public R router() {
        return mRouter;
    }

    public RouterCallback routerImplementation() {
        Logger.v(this, "routerImplementation");

        return new SafeRouterCallback(mView.provideRouterImplementation(), this);
    }

    @SuppressWarnings("unchecked")
    private static void dispatchOnCreate(View v) {
        ArchitectureDelegateHolder holder = v.architectureHolder();

        ArchitectureDelegate oldDelegate = holder.get();


        if (oldDelegate == null) {
            ArchitectureDelegate newDelegate = new ArchitectureDelegate(v.screenConfigurator());

            holder.set(newDelegate);
            newDelegate.onCreate();
        } else {
            oldDelegate.replaceView(v);
        }
    }

    private void replaceView(V v) {
        mView = v;
        mView.setCallback(this);
    }

    public static void onCreateView(View v) {
        dispatchOnCreate(v);
    }

    public static void onStart(View v) {
        v.architectureHolder().get().onStart();
    }

    public static void onStop(View v) {
        v.architectureHolder().get().onStop();
    }


    @SuppressWarnings("unchecked")
    private void onCreate() {
        Logger.v(this, "onCreate");

        mPresenter.onCreate(this);
        mInteractor.onCreate(this);
        mRouter.onCreate(this);

        mView.setCallback(this);
    }

    @SuppressWarnings("unchecked")
    private void onStart() {
        Logger.v(this, "onStart");

        mPresenter.onViewAttached(mView);
        mView.onAttachToPresenter(mPresenter);

        mViewActions.setReceiver(mView);
    }

    private void onStop() {
        Logger.v(this, "onStop");

        mViewActions.removeReceiver();

        Logger.v(this, "onStop, mResources.size() " + mResources.size());

        mResources.clear();
    }

    void onDestroy() {
        Logger.v(this, "onStop");

        mPresenter.onDestroy();
        mInteractor.onDestroy();
    }

    @Override
    public CachedActions<? extends View> get() {
        return mViewActions;
    }

    @Override
    public <T> Observer<T> viewObserver(Action<T> onNext) {
        return new UnsubscribeOnStopObserver<>(onNext);
    }

    @Override
    public <T> Observer<T> stateObserver(Action<T> onNext) {
        return new UnsubscribeOnStopObserver<>(onNext);
    }

    final class UnsubscribeOnStopObserver<T> implements Observer<T> {
        private final Action<T> mOnNext;

        UnsubscribeOnStopObserver(Action<T> onNext) {
            mOnNext = onNext;
        }

        @Override
        @CallSuper
        public void onSubscribe(Disposable d) {
            Logger.v(this, "onSubscribe");
            mResources.add(d);
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
