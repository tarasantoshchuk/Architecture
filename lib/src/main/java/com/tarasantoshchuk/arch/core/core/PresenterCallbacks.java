package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.disposables.Disposable;

public interface PresenterCallbacks<V, R, I> {
    R router();
    I interactor();

    void applyOnView(Action<V> action);

    void unsubscribeOnDetach(Disposable disposable);
    void unsubscribeOnDestroy(Disposable disposable);
}
