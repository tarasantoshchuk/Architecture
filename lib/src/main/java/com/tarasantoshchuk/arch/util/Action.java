package com.tarasantoshchuk.arch.util;


public interface Action<T> {
    void apply(T t);
}
