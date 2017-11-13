package com.tarasantoshchuk.arch.core.interactor.impl;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.util.Logger;

public abstract class BaseInteractor implements Interactor {

    @Override
    public void onCreate() {
        Logger.v(this, "onCreate");
    }

    @Override
    public void onDestroy() {
        Logger.v(this, "onDestroy");
    }
}
