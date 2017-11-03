package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.di.BaseScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditPresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditRouter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;

class EditScreenConfigurator extends BaseScreenConfigurator<EditView,EditPresenter,EditInteractor,EditRouter> {
    EditScreenConfigurator(EditView view) {
        super(view);
    }

    @Override
    public EditPresenter presenter() {
        return new EditPresenterImpl();
    }

    @Override
    public EditInteractor interactor() {
        return new EditInteractorImpl();
    }

    @Override
    public EditRouter router() {
        return new EditRouterImpl();
    }
}
