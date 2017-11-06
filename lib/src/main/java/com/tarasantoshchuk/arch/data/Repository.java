package com.tarasantoshchuk.arch.data;

import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public interface Repository<T> extends ReadOnlyRepository<T> {
    Single<Null> set(T t);

    default Single<Null> setOnMainThread(T t) {
        return set(t)
                .observeOn(AndroidSchedulers.mainThread());
    }
}
