package com.tarasantoshchuk.arch.data;

import com.tarasantoshchuk.arch.util.Null;

import io.reactivex.Single;

public interface Repository<T> extends ReadOnlyRepository<T> {
    Single<Null> set(T t);

    Single<Null> setOnMainThread(T t);
}
