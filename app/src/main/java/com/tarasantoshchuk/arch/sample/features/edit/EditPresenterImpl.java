package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditRouter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView.EditPresenter;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

class EditPresenterImpl extends BasePresenter<EditView, EditRouter, EditInteractor> implements EditPresenter {
    private BehaviorSubject<String> mSavedText = BehaviorSubject.create();
    private BehaviorSubject<Boolean> mUiEnabled = BehaviorSubject.createDefault(false);

    @Override
    public void onCreate() {
        interactor()
                .getSavedText()
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                        mSavedText.onNext(s);
                        mUiEnabled.onNext(true);
                    }
                });

    }

    @Override
    public void onViewAttached(EditView view) {
        super.onViewAttached(view);

        viewObservable(view.saveClicks())
                .subscribe(__ -> saveText());

        viewObservable(view.textChanged())
                .subscribe(this::updateText);
    }

    @Override
    public Observable<String> textLoaded() {
        return mSavedText;
    }

    @Override
    public Observable<Boolean> uiEnabled() {
        return mUiEnabled;
    }

    private void updateText(String text) {
        if (text.equals(mSavedText.getValue())) {
            return;
        }

        mSavedText.onNext(text);
    }

    private void saveText() {
        modelObservable(interactor().saveText(mSavedText.getValue()))
                .subscribe(aNull -> router().finish());
    }
}
