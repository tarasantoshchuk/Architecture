package com.tarasantoshchuk.arch.data;

import io.reactivex.Observable;

public interface ReadOnlyRepository<T> {
    Observable<T> get();

    Observable<T> getOnMainThread();
}
