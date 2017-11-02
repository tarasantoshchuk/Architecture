package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.interactor.Interactor;
import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver;
import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.sample.utils.Null;

import io.reactivex.Observable;
import io.reactivex.Single;

interface Contract {
    interface EditView extends View<EditPresenter> {
        void setText(String text);

        Observable<Null> saveClicks();
        Observable<String> textChanged();
    }

    interface EditPresenter extends Presenter<EditView, EditRouter, EditInteractor> {

    }

    interface EditInteractor extends Interactor<EditPresenter> {
        Observable<String> getSavedText();
        Single<Null> saveText(String value);
    }

    interface EditRouter extends Router<EditPresenter, Screen> {
    }
}
