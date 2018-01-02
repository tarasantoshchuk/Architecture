package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.sample.utils.Irrelevant;

import io.reactivex.Observable;

interface Contract {
    interface MainView extends RootView<MainView.MainPresenter> {
        Observable<Irrelevant> editClicks();
        Observable<Irrelevant> openOtherClicks();



        interface MainPresenter extends Presenter<MainView, MainRouter, MainInteractor> {
            Observable<String> text();
            Observable<Boolean> editEnabled();
        }
    }

    interface MainInteractor extends Interactor {
        Observable<String> savedText();
    }

    interface MainRouter extends Router<MainView.MainPresenter, Screens> {
        void openEditScreen();
        void openScreenWithFragment();
    }
}
