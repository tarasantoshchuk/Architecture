package com.tarasantoshchuk.arch.core.interactor.impl;


import com.tarasantoshchuk.arch.core.InteractorCallbacks;
import com.tarasantoshchuk.arch.core.interactor.Interactor;

public abstract class BaseInteractor<P> implements Interactor<P> {
    private InteractorCallbacks<P> mCallbacks;

    @Override
    public void onCreate(InteractorCallbacks<P> callbacks) {
        mCallbacks = callbacks;
    }

    @Override
    public void onDestroy() {
        mCallbacks = null;
    }

    protected final P presenter() {
        return mCallbacks.presenter();
    }
}
