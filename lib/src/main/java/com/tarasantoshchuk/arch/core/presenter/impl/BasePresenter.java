package com.tarasantoshchuk.arch.core.presenter.impl;


import com.tarasantoshchuk.arch.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observable;
import io.reactivex.Observer;

public abstract class BasePresenter<V, R, I> implements Presenter<V, R, I> {
    private PresenterCallbacks<V, R, I> mCallbacks;

    @Override
    public final void onCreate(PresenterCallbacks<V, R, I> callbacks) {
        Presenter.super.onCreate(callbacks);
        mCallbacks = callbacks;

        onCreate();
    }

    protected abstract void onCreate();

    @Override
    public void onDestroy() {
        Presenter.super.onDestroy();
        mCallbacks = null;
    }

    private <T> Observer<T> viewObserver(Action<T> onNext) {
        return mCallbacks.viewObserver(onNext);
    }

    protected final <T> void observeView(Observable<T> observable, Action<T> onNext) {
        observable
                .subscribe(
                        viewObserver(onNext)
                );
    }

    protected final <T> void observeView(Observable<T> observable, Runnable onNext) {
        observable
                .subscribe(
                        viewObserver(__ -> onNext.run())
                );
    }

    protected final R router() {
        return mCallbacks.router();
    }

    protected final I interactor() {
        return mCallbacks.interactor();
    }
}
