package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.sample.utils.Null;

import io.reactivex.Observable;

interface Contract {
    interface MainView extends View<MainPresenter> {
        Observable<Null> editClicks();
        void setText(String text);
        void enableEdit(boolean isLocked);
    }

    interface MainPresenter extends Presenter<MainView, MainRouter, MainInteractor> {
    }

    interface MainInteractor extends Interactor<MainPresenter> {
        Observable<String> savedText();
    }

    interface MainRouter extends Router<MainPresenter, Screens> {
        void openEditScreen();
    }
}
