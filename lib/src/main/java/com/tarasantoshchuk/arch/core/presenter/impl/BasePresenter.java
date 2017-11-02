package com.tarasantoshchuk.arch.core.presenter.impl;


import com.tarasantoshchuk.arch.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.util.Action;

public abstract class BasePresenter<V, R, I> implements Presenter<V, R, I> {
    private PresenterCallbacks<V, R, I> mCallbacks;

    @Override
    public void onCreate(PresenterCallbacks<V, R, I> callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    public void onDestroy() {
        mCallbacks = null;
    }

    protected final void applyOnView(Action<V> action) {
        mCallbacks.applyOnView(action);
    }

    protected final R router() {
        return mCallbacks.router();
    }

    protected final I interactor() {
        return mCallbacks.interactor();
    }
}
