package com.tarasantoshchuk.arch.core;


import android.arch.lifecycle.ViewModel;

public final class ArchitectureDelegateHolder extends ViewModel {
    private ArchitectureDelegate mArchitectureDelegate;

    ArchitectureDelegate get() {
        return mArchitectureDelegate;
    }

    void set(ArchitectureDelegate delegate) {
        mArchitectureDelegate = delegate;
    }

    @Override
    protected void onCleared() {
        mArchitectureDelegate.onDestroy();
    }
}
