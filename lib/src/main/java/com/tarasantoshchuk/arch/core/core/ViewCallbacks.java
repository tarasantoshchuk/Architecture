package com.tarasantoshchuk.arch.core.core;


import com.tarasantoshchuk.arch.util.Action;

import io.reactivex.Observer;

public interface ViewCallbacks<R> {
    R router();

    <T> Observer<T> stateObserver(Action<T> onNext);
}
