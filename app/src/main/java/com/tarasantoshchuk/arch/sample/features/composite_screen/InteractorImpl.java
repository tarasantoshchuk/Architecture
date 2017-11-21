package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.interactor.impl.BaseInteractor;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.subjects.PublishSubject;

class InteractorImpl extends BaseInteractor implements Interactor {
    private PublishSubject<Boolean> mSwitch = PublishSubject.create();
    private PublishSubject<String> mText = PublishSubject.create();
    private PublishSubject<Null> mClick = PublishSubject.create();

    @Override
    public PublishSubject<Boolean> switchPosition() {
        return mSwitch;
    }

    @Override
    public PublishSubject<String> currentText() {
        return mText;
    }

    @Override
    public PublishSubject<Null> click() {
        return mClick;
    }
}
