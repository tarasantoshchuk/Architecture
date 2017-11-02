package com.tarasantoshchuk.arch.core.view;


import com.tarasantoshchuk.arch.core.ArchitectureDelegate;
import com.tarasantoshchuk.arch.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;

public interface View<P> extends RouterCallbackProvider {
    ArchitectureDelegateHolder architectureHolder();
    ScreenConfigurator screenConfigurator();

    void setCallback(ViewCallbacks<? extends Router> callbacks);
}
