package com.tarasantoshchuk.arch.core;


import com.tarasantoshchuk.arch.util.Action;

public interface PresenterCallbacks<V, R, I> {
    void applyOnView(Action<V> action);
    R router();
    I interactor();
}
