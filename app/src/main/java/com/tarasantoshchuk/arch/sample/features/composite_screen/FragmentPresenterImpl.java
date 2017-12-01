package com.tarasantoshchuk.arch.sample.features.composite_screen;

import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.FragmentView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.FragmentView.FragmentViewPresenter;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Router;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class FragmentPresenterImpl extends BasePresenter<FragmentView, Router, Interactor> implements FragmentViewPresenter {
    private BehaviorSubject<Boolean> mSwitch = BehaviorSubject.createDefault(false);
    private BehaviorSubject<String> mText = BehaviorSubject.createDefault("");

    @Override
    protected void onCreate() {
        super.onCreate();

        interactor().switchPosition().subscribe(new SimpleObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                super.onNext(aBoolean);
                mSwitch.onNext(aBoolean);
            }
        });

        mText.subscribe(interactor().currentText());
    }

    @Override
    public void onViewAttached(FragmentView view) {
        observeView(view.textEdited(),
                this::textChanged);
    }

    private void textChanged(String text) {
        mText.onNext(text);
    }

    @Override
    public Observable<Boolean> switchChanged() {
        return mSwitch;
    }
}
