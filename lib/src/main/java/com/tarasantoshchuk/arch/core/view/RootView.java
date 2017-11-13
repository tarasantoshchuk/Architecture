package com.tarasantoshchuk.arch.core.view;


import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.routing.RouterCallbackProvider;

public interface RootView<P> extends View<P>, RouterCallbackProvider {
    ArchitectureDelegateHolder architectureHolder();
}
