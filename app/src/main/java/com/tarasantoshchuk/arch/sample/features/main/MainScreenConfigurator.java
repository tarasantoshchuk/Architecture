package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.di.BaseScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;

class MainScreenConfigurator extends BaseScreenConfigurator<MainView,MainPresenter,MainInteractor,MainRouter> {
    MainScreenConfigurator(MainView view) {
        super(view);
    }

    @Override
    public MainPresenter presenter() {
        return new MainPresenterImpl();
    }

    @Override
    public MainInteractor interactor() {
        return new MainInteractorImpl();
    }

    @Override
    public MainRouter router() {
        return new MainRouterImpl();
    }
}
