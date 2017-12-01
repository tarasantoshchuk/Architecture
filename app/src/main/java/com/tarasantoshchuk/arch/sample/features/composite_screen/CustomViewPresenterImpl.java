package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.sample.utils.SimpleObserver;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class CustomViewPresenterImpl extends BasePresenter<Contract.CustomView, Contract.Router, Contract.Interactor> implements Contract.CustomView.CustomViewPresenter {
    private BehaviorSubject<Boolean> mSwitched = BehaviorSubject.createDefault(false);
    private BehaviorSubject<Null> mClick = BehaviorSubject.createDefault(Null.INSTANCE);

    @Override
    protected void onCreate() {
        super.onCreate();
        interactor().switchPosition().subscribe(new SimpleObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                super.onNext(aBoolean);
                mSwitched.onNext(aBoolean);
            }
        });

        mClick.subscribe(interactor().click());
    }

    @Override
    public Observable<Boolean> switchChanged() {
        return mSwitched;
    }

    @Override
    public void onViewAttached(Contract.CustomView view) {
        observeView(view.touched(), this::clicked);
    }

    private void clicked(Null n) {
        mClick.onNext(n);
    }
}
