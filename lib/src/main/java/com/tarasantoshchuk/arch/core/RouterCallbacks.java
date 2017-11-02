package com.tarasantoshchuk.arch.core;

import com.tarasantoshchuk.arch.core.routing.RouterCallback;

public interface RouterCallbacks<P> {
    P presenter();
    RouterCallback routerImplementation();
}
