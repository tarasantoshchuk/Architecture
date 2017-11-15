package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.presenter.impl.BasePresenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.HostView;
import com.tarasantoshchuk.arch.sample.features.composite_screen.Contract.Interactor;

class HostViewPresenterImpl extends BasePresenter<HostView, Router, Interactor> implements HostView.HostViewPresenter {
    @Override
    public void onViewAttached(HostView view) {
        super.onViewAttached(view);
    }
}
