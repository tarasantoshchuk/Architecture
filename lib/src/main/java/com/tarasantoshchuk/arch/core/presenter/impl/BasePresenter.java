package com.tarasantoshchuk.arch.core.presenter.impl;


import com.tarasantoshchuk.arch.core.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;

public abstract class BasePresenter<V, R, I> implements Presenter<V, R, I> {
    private PresenterCallbacks<V, R, I> mCallbacks;

    @Override
    public final void onCreate(PresenterCallbacks<V, R, I> callbacks) {
        mCallbacks = callbacks;

        onCreate();
    }

    //for overriding
    protected void onCreate() {

    }

    @Override
    public void onDestroy() {
        mCallbacks = null;
    }

    private <T> SingleObserver<T> modelObserver(Action<T> onNext) {
        return mCallbacks.modelObserver(onNext);
    }

    protected final <T> void observeModel(Single<T> observable, final Runnable onNext) {
        observable
                .subscribe(
                        modelObserver(new Action<Object>() {
                            @Override
                            public void apply(Object __) {
                                onNext.run();
                            }
                        })
                );
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

    protected final <T> void observeView(Observable<T> observable, final Runnable onNext) {
        observable
                .subscribe(
                        viewObserver(new Action<Object>() {
                            @Override
                            public void apply(Object __) {
                                onNext.run();
                            }
                        })
                );
    }

    protected final R router() {
        return mCallbacks.router();
    }

    protected final I interactor() {
        return mCallbacks.interactor();
    }
}
