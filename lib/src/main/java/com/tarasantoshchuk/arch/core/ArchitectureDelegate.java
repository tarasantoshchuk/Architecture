package com.tarasantoshchuk.arch.core;


import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallback;
import com.tarasantoshchuk.arch.util.Action;
import com.tarasantoshchuk.arch.util.CachedActions;
import com.tarasantoshchuk.arch.core.view.View;

import io.reactivex.disposables.CompositeDisposable;

public final class ArchitectureDelegate<
        V extends View,
        P extends Presenter,
        I extends Interactor,
        R extends Router>

    implements

        PresenterCallbacks<V, R, I>,
        ViewCallbacks<R>,
        RouterCallbacks<P>,
        InteractorCallbacks<P>
{
    private V mView;
    private P mPresenter;
    private I mInteractor;
    private R mRouter;

    private boolean mRouterCallbackAvailable;
    private CachedActions<V> mViewActions = new CachedActions<>();
    private CompositeDisposable mResources = new CompositeDisposable();

    private ArchitectureDelegate(ScreenConfigurator<V, P, I, R> configurator) {
        mView = configurator.view();
        mPresenter = configurator.presenter();
        mInteractor = configurator.interactor();
        mRouter = configurator.router();
    }

    public void applyOnView(Action<V> action) {
        mViewActions.submit(action);
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
        if (!mRouterCallbackAvailable) {
            throw new IllegalStateException();
        }

        return mView.provideRouterImplementation();
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
            oldDelegate.mView = v;
        }

        holder.get().onCreateView();
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

    public static void onDestroyView(View v) {
        v.architectureHolder().get().onDestroyView();
    }


    @SuppressWarnings("unchecked")
    private void onCreate() {
        mPresenter.onCreate(this);
        mInteractor.onCreate(this);
        mRouter.onCreate(this);

        mView.setCallback(this);
    }

    private void onCreateView() {
        mRouterCallbackAvailable = true;
    }

    @SuppressWarnings("unchecked")
    private void onStart() {
        mViewActions.setReceiver(mView);
        mPresenter.onViewAttached(mView);
    }

    private void onStop() {
        mViewActions.removeReceiver();
    }

    private void onDestroyView() {
        mRouterCallbackAvailable = false;
    }

    void onDestroy() {
        mPresenter.onDestroy();
        mInteractor.onDestroy();

        mResources.dispose();
    }
}
