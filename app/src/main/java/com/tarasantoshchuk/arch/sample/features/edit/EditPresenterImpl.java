package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditPresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditRouter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;
import com.tarasantoshchuk.arch.sample.utils.Null;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class EditPresenterImpl extends BasePresenter<EditView, EditRouter, EditInteractor> implements EditPresenter {
    private BehaviorSubject<String> mSavedText = BehaviorSubject.create();

    @Override
    public void onViewAttached(EditView view) {
        view
                .saveClicks()
                .subscribe(new SimpleObserver<Null>() {
                    @Override
                    public void onNext(Null aNull) {
                        interactor()
                                .saveText(mSavedText.getValue())
                                .subscribe(new SingleObserver<Null>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(Null aNull) {
                                        router().finish();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                });
                    }
                });

        view
                .textChanged()
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        if (s.equals(mSavedText.getValue())) {
                            return;
                        }

                        mSavedText.onNext(s);
                    }
                });
    }

    @Override
    public void onCreate(PresenterCallbacks<EditView, EditRouter, EditInteractor> callbacks) {
        mSavedText
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        applyOnView(view -> view.setText(s));
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
