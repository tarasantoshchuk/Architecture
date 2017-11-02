package com.tarasantoshchuk.arch.sample.features.edit;


import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditInteractor;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditPresenter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditRouter;
import com.tarasantoshchuk.arch.sample.features.edit.Contract.EditView;

public class EditScreenConfigurator implements ScreenConfigurator<EditView,EditPresenter,EditInteractor,EditRouter> {
    @Override
    public EditPresenter presenter() {
        return new EditPresenterImpl();
    }

    @Override
    public EditInteractor interactor() {
        return null;
    }

    @Override
    public EditRouter router() {
        return null;
    }

    @Override
    public EditView view() {
        return null;
    }
}
