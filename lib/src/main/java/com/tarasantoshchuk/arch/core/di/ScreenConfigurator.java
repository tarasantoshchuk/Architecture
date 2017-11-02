package com.tarasantoshchuk.arch.core.di;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.View;

public interface ScreenConfigurator<V extends View, P extends Presenter, I extends Interactor, R extends Router> {
    P presenter();
    I interactor();
    R router();
    V view();
}
