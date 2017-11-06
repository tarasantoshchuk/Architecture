package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.data.TextRepository;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;

import io.reactivex.Observable;

class MainInteractorImpl extends BaseInteractor<MainPresenter> implements MainInteractor {
    private TextRepository mTextRepository = TextRepository.getInstance();

    @Override
    public Observable<String> savedText() {
        return mTextRepository.getOnMainThread();
    }
}
