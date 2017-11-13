package com.tarasantoshchuk.arch.core.di;


import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.View;

public interface ScreenConfigurator<V extends View, P extends Presenter, R extends Router> {
    P presenter();
    R router();
    V view();
}
