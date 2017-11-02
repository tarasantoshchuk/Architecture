package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainInteractor;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainView;

public class MainScreenConfigurator implements ScreenConfigurator<MainView, MainPresenter, MainInteractor, MainRouter> {
    MainScreenConfigurator(MainView view) {
        mView = view;
    }

    private final MainView mView;

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

    @Override
    public MainView view() {
        return mView;
    }
}
