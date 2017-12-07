package com.tarasantoshchuk.arch.core.presenter.impl;


import com.tarasantoshchuk.arch.core.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public abstract class BasePresenter<V, R, I> implements Presenter<V, R, I> {
    private PresenterCallbacks<V, R, I> mCallbacks;

    @Override
    public final void onCreate(PresenterCallbacks<V, R, I> callbacks) {
        Presenter.super.onCreate(callbacks);
        mCallbacks = callbacks;

        onCreate();
    }

    //for overriding
    protected void onCreate() {

    }

    @Override
    public void onDestroy() {
        Presenter.super.onDestroy();
        mCallbacks = null;
    }

    protected void applyOnView(Action<V> action) {
        mCallbacks
                .applyOnView(action);
    }

    protected <T> Observable<T> viewObservable(Observable<T> source) {
        return source
                .doOnSubscribe(mCallbacks::unsubscribeOnDetach);
    }

    protected <T> Single<T> modelObservable(Single<T> source) {
        return source
                .doOnSubscribe(mCallbacks::unsubscribeOnDestroy);
    }

    protected Completable modelObservable(Completable source) {
        return source
                .doOnSubscribe(mCallbacks::unsubscribeOnDestroy);
    }

    protected final R router() {
        return mCallbacks.router();
    }

    protected final I interactor() {
        return mCallbacks.interactor();
    }
}
