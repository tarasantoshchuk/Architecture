package com.tarasantoshchuk.arch.core.view;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;
import com.tarasantoshchuk.arch.util.Logger;

public interface View<P> extends RouterCallbackProvider {
    ArchitectureDelegateHolder architectureHolder();
    ScreenConfigurator screenConfigurator();

    @CallSuper
    default void onAttachToPresenter(P presenter) {
        Logger.v(this, "onAttachToPresenter, presenter " + presenter);
    }

    void setCallback(ViewCallbacks<? extends Router> callbacks);
}
