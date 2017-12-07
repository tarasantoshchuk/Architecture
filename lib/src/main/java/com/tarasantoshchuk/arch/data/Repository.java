package com.tarasantoshchuk.arch.data;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public interface Repository<T> extends ReadOnlyRepository<T> {
    Completable set(T t);

    default Completable setOnMainThread(T t) {
        return set(t)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
