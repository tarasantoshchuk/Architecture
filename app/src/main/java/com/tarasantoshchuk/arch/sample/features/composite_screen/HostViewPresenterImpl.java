package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.HostView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.HostView.HostViewPresenter;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Router;
import com.tarasantoshchuk.arch.sample.utils.Irrelevant;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class HostViewPresenterImpl extends BasePresenter<HostView, Router, Interactor> implements HostViewPresenter {
    private BehaviorSubject<Boolean> mSwitch = BehaviorSubject.createDefault(false);
    private BehaviorSubject<String> mText = BehaviorSubject.createDefault("");
    private BehaviorSubject<Irrelevant> mClick = BehaviorSubject.createDefault(Irrelevant.INSTANCE);

    @Override
    protected void onCreate() {
        super.onCreate();
        mSwitch.subscribe(interactor().switchPosition());

        interactor().currentText().subscribe(new SimpleObserver<String>() {
            @Override
            public void onNext(String s) {
                super.onNext(s);
                mText.onNext(s);
            }
        });

        interactor().click().subscribe(new SimpleObserver<Irrelevant>() {
            @Override
            public void onNext(Irrelevant n) {
                super.onNext(n);
                mClick.onNext(n);
            }
        });
    }

    @Override
    public void onViewAttached(HostView view) {
        super.onViewAttached(view);

        viewObservable(view.switched())
                .subscribe(this::changed);
    }

    private void changed(boolean v) {
        mSwitch.onNext(v);
    }

    @Override
    public Observable<Irrelevant> click() {
        return mClick;
    }

    @Override
    public Observable<String> textChanged() {
        return mText;
    }
}
