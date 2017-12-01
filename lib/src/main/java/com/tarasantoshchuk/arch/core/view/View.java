package com.tarasantoshchuk.arch.core.view;


import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;

public interface View<P> extends RouterCallbackProvider {
    ScreenConfigurator screenConfigurator();

    void onAttachToPresenter(P presenter);

    ViewId viewId();

    void setCallback(ViewCallbacks<? extends Router> callbacks);
}
