package com.tarasantoshchuk.arch.sample.features.composite_screen;


import com.tarasantoshchuk.arch.core.presenter.Presenter;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.view.View;

public interface Contract {
    interface HostView extends View<HostView.HostViewPresenter> {


        interface HostViewPresenter extends Presenter<HostView, Router, Interactor> {

        }
    }

    interface FragmentView extends View<FragmentView.FragmentViewPresenter> {


        interface FragmentViewPresenter extends Presenter {

        }
    }

    interface CustomView extends View<CustomView.CustomViewPresenter> {


        interface CustomViewPresenter extends Presenter {

        }
    }

    interface Interactor extends com.tarasantoshchuk.arch.core.interactor.Interactor {

    }
}
