package com.tarasantoshchuk.arch.data;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public interface ReadOnlyRepository<T> {
    Observable<T> get();

    default Observable<T> getOnMainThread() {
        return get()
                .observeOn(AndroidSchedulers.mainThread());
    }
}
