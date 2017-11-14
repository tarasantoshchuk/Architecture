package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.di.BaseRootScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView.MainPresenter;

class MainScreenConfigurator extends BaseRootScreenConfigurator<MainView, MainPresenter,MainInteractor,MainRouter> {
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
