package com.tarasantoshchuk.arch.sample.features.main;


import com.tarasantoshchuk.arch.core.routing.router_impl.BaseRouter;
import com.tarasantoshchuk.arch.sample.core.routing.Screens;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainPresenter;
import com.tarasantoshchuk.arch.sample.features.main.Contract.MainRouter;
import com.tarasantoshchuk.arch.util.Logger;

public class MainRouterImpl extends BaseRouter<MainPresenter, Screens> implements MainRouter {
    @Override
    public void openEditScreen() {
        Logger.v(this, "openEditScreen");

        callback().startScreen(Screens.EDIT);
    }
}
