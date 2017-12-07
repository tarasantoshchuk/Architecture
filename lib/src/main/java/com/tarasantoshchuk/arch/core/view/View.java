package com.tarasantoshchuk.arch.core.view;


import android.support.annotation.CallSuper;

import com.tarasantoshchuk.arch.core.core.ViewCallbacks;
import com.tarasantoshchuk.arch.core.di.ScreenConfigurator;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;
import com.tarasantoshchuk.arch.util.log.Logger;

public interface View<P> extends RouterCallbackProvider {
    ScreenConfigurator screenConfigurator();

    @CallSuper
    default void onAttachToPresenter(P presenter) {
        Logger.v(this, "onAttachToPresenter, presenter " + presenter);
    }

    default ViewId viewId() {
        return new ViewId(getClass().getSimpleName());
    }

    void setCallback(ViewCallbacks callbacks);
}
