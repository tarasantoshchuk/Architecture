package com.tarasantoshchuk.arch.core.di;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;

public interface RootScreenConfigurator<RV extends RootView, P extends Presenter, I extends Interactor, R extends Router>
        extends ScreenConfigurator<RV, P, R> {
    I interactor();
    RV view();
}
