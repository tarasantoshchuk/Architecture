package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.RootView;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Observable;
import io.reactivex.Single;

interface Contract {
    interface EditView extends RootView<EditView.EditPresenter> {
        Observable<Null> saveClicks();
        Observable<String> textChanged();

        interface EditPresenter extends Presenter<EditView, EditRouter, EditInteractor> {
            Observable<String> textLoaded();
            Observable<Boolean> uiEnabled();
        }
    }

    interface EditInteractor extends Interactor {
        Observable<String> getSavedText();
        Single<Null> saveText(String value);
    }

    interface EditRouter extends Router<EditView.EditPresenter, Screens> {
    }
}
