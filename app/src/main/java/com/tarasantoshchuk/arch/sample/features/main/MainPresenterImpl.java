package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.PresenterCallbacks;
import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;
import com.tarasantoshchuk.arch.sample.utils.Null;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;

import io.reactivex.subjects.BehaviorSubject;

class MainPresenterImpl extends BasePresenter<MainView, MainRouter, MainInteractor> implements MainPresenter {
    private BehaviorSubject<String> mText = BehaviorSubject.create();
    private BehaviorSubject<Boolean> mUiEnabled = BehaviorSubject.createDefault(false);

    @Override
    public void onCreate(PresenterCallbacks<MainView, MainRouter, MainInteractor> callbacks) {
        super.onCreate(callbacks);

        mText
                .subscribe(new SimpleObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        applyOnView(view -> view.setText(s));
                    }
                });

        mUiEnabled
                .subscribe(new SimpleObserver<Boolean>() {
                    @Override
                    public void onNext(final Boolean isEnabled) {
                        applyOnView(view -> view.enableEdit(isEnabled));
                    }
                });
    }

    @Override
    public void onViewAttached(MainView view) {
        view
                .editClicks()
                .subscribe(new SimpleObserver<Null>() {
                    @Override
                    public void onNext(Null aNull) {
                        router().openEditScreen();
                    }
                });
    }
}
