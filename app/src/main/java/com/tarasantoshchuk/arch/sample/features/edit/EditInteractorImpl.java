package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.data.TextRepository;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditPresenter;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Observable;
import io.reactivex.Single;

public class EditInteractorImpl extends BaseInteractor<EditPresenter> implements EditInteractor {
    private TextRepository mTextRepository = TextRepository.getInstance();

    @Override
    public Observable<String> getSavedText() {
        return mTextRepository.getOnMainThread();
    }

    @Override
    public Single<Null> saveText(String value) {
        return mTextRepository
                .setOnMainThread(value);
    }
}
