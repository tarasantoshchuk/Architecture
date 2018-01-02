package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.view.View;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.sample.utils.Irrelevant;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public interface Contract {

    interface HostView extends View<HostView.HostViewPresenter> {
        Observable<Boolean> switched();

        interface HostViewPresenter extends Presenter<HostView, Router, Interactor> {
            Observable<String> textChanged();

            Observable<Irrelevant> click();
        }
    }

    interface FragmentView extends View<FragmentView.FragmentViewPresenter> {
        Observable<String> textEdited();

        interface FragmentViewPresenter extends Presenter<FragmentView, Router, Interactor> {
            Observable<Boolean> switchChanged();
        }
    }

    interface CustomView {
        Observable<Irrelevant> touched();

        interface CustomViewPresenter extends Presenter<CustomView, Router, Interactor> {
            Observable<Boolean> switchChanged();
        }
    }

    interface Interactor extends com.tarasantoshchuk.arch.core.interactor.Interactor {
        PublishSubject<Boolean> switchPosition();

        PublishSubject<String> currentText();

        PublishSubject<Irrelevant> click();
    }

    interface Router extends com.tarasantoshchuk.arch.core.routing.Router<HostView.HostViewPresenter, Screens> {

    }
}
