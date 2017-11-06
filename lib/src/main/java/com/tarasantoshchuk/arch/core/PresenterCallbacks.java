package com.tarasantoshchuk.arch.core;


import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observer;

public interface PresenterCallbacks<V, R, I> {
    R router();
    I interactor();

    <T> Observer<T> viewObserver(Action<T> onNext);
}
