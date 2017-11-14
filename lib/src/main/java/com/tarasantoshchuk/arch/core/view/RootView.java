package com.tarasantoshchuk.arch.core.view;


import com.tarasantoshchuk.arch.core.core.ArchitectureDelegateHolder;

public interface RootView<P> extends View<P> {
    ArchitectureDelegateHolder architectureHolder();
}
