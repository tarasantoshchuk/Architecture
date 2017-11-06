package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;
import com.tarasantoshchuk.arch.util.Logger;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class MainPresenterImpl extends BasePresenter<MainView, MainRouter, MainInteractor> implements MainPresenter {
    private BehaviorSubject<String> mText = BehaviorSubject.create();
    private BehaviorSubject<Boolean> mUiEnabled = BehaviorSubject.createDefault(false);

    @Override
    public void onCreate() {
        interactor()
                .savedText()
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(String o) {
                        super.onNext(o);

                        mText.onNext(o);
                        mUiEnabled.onNext(true);
                    }
                });
    }

    @Override
    public void onViewAttached(MainView view) {
        super.onViewAttached(view);

        observeView(
                view.editClicks(),
                this::openEditScreen
        );
    }

    @Override
    public Observable<String> text() {
        return mText;
    }

    @Override
    public Observable<Boolean> editEnabled() {
        return mUiEnabled;
    }

    private void openEditScreen() {
        Logger.v(this, "onEditClick received");
        router().openEditScreen();
    }
}
