package com.tarasantoshchuk.arch.core.presenter;


import com.tarasantoshchuk.arch.core.PresenterCallbacks;

public interface Presenter<V, R, I> {
    void onViewAttached(V view);
    void onCreate(PresenterCallbacks<V, R, I> callbacks);
    void onDestroy();
}
