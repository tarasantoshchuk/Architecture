package com.tarasantoshchuk.arch.core.interactor;


import com.tarasantoshchuk.arch.core.InteractorCallbacks;

public interface Interactor<P> {
    void onCreate(InteractorCallbacks<P> callbacks);
    void onDestroy();
}
