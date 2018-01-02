package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;
import com.tarasantoshchuk.arch.sample.utils.Irrelevant;

import io.reactivex.subjects.PublishSubject;

class InteractorImpl extends BaseInteractor implements Interactor {
    private PublishSubject<Boolean> mSwitch = PublishSubject.create();
    private PublishSubject<String> mText = PublishSubject.create();
    private PublishSubject<Irrelevant> mClick = PublishSubject.create();

    @Override
    public PublishSubject<Boolean> switchPosition() {
        return mSwitch;
    }

    @Override
    public PublishSubject<String> currentText() {
        return mText;
    }

    @Override
    public PublishSubject<Irrelevant> click() {
        return mClick;
    }
}
