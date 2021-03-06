package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.di.BaseRootScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditRouter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView.EditPresenter;

class EditScreenConfigurator extends BaseRootScreenConfigurator<EditView, EditPresenter,EditInteractor,EditRouter> {
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
