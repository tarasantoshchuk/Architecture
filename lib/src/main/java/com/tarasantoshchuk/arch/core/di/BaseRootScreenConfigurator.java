package com.tarasantoshchuk.arch.core.di;

import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;

public abstract class BaseRootScreenConfigurator<RV extends RootView, P extends Presenter, I extends Interactor, R extends Router>
    implements RootScreenConfigurator<RV, P, I, R> {

    private final RV mView;

    protected BaseRootScreenConfigurator(RV view) {
        mView = view;
    }

    @Override
    public final RV view() {
        return mView;
    }

}
