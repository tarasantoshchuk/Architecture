package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;

public interface PresenterCallbacks<V, R, I> {
    R router();
    I interactor();

    <T> Observer<T> viewObserver(Action<T> onNext);
    <T> SingleObserver<T> modelObserver(Action<T> onNext);
}
