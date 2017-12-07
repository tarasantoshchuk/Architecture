package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.data.TextRepository;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class EditInteractorImpl extends BaseInteractor implements EditInteractor {
    private TextRepository mTextRepository = TextRepository.getInstance();

    @Override
    public Observable<String> getSavedText() {
        return mTextRepository.getOnMainThread();
    }

    @Override
    public Completable saveText(String value) {
        return mTextRepository
                .setOnMainThread(value);
    }
}
