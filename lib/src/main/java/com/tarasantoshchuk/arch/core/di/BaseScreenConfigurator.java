package com.tarasantoshchuk.arch.core.di;

import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.View;

public abstract class BaseScreenConfigurator<V extends View, P extends Presenter, I extends Interactor, R extends Router>
    implements ScreenConfigurator<V, P, I, R> {

    private final V mView;

    protected BaseScreenConfigurator(V view) {
        mView = view;
    }

    @Override
    public final V view() {
        return mView;
    }
}
