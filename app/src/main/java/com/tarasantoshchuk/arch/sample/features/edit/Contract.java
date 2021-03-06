package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.sample.utils.Irrelevant;

import io.reactivex.Completable;
import io.reactivex.Observable;

interface Contract {
    interface EditView extends RootView<EditView.EditPresenter> {
        Observable<Irrelevant> saveClicks();
        Observable<String> textChanged();

        interface EditPresenter extends Presenter<EditView, EditRouter, EditInteractor> {
            Observable<String> textLoaded();
            Observable<Boolean> uiEnabled();
        }
    }

    interface EditInteractor extends Interactor {
        Observable<String> getSavedText();
        Completable saveText(String value);
    }

    interface EditRouter extends Router<EditView.EditPresenter, Screens> {
    }
}
