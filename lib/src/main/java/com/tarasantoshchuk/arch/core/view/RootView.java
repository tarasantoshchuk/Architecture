package com.tarasantoshchuk.arch.core.view;


import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;
import com.tarasantoshchuk.arch.core.di.RootScreenConfigurator;

public interface RootView<P> extends View<P> {
    ArchitectureDelegateHolder architectureHolder();

    @Override
    RootScreenConfigurator screenConfigurator();
}
