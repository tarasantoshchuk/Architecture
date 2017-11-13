package com.tarasantoshchuk.arch.core.core;


import android.arch.lifecycle.ViewModel;

public final class ArchitectureDelegateHolder extends ViewModel {
    private RootArchitectureDelegate mArchitectureDelegate;

    RootArchitectureDelegate get() {
        return mArchitectureDelegate;
    }

    void set(RootArchitectureDelegate delegate) {
        mArchitectureDelegate = delegate;
    }

    @Override
    protected void onCleared() {
        mArchitectureDelegate.onDestroy();
    }
}
