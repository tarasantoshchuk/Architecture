package com.tarasantoshchuk.arch.core.view;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.Router;
import com.tarasantoshchuk.arch.util.Logger;

public interface View<P> {
    RootScreenConfigurator screenConfigurator();

    @CallSuper
    default void onAttachToPresenter(P presenter) {
        Logger.v(this, "onAttachToPresenter, presenter " + presenter);
    }

    default String tag() {
        return getClass().getSimpleName();
    }

    void setCallback(ViewCallbacks<? extends Router> callbacks);
}
