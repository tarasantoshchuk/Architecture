package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;

import io.reactivex.Observable;

class MainInteractorImpl extends BaseInteractor<MainPresenter> implements MainInteractor {
    @Override
    public Observable<String> savedText() {
        return Observable.just("hi");
    }
}
