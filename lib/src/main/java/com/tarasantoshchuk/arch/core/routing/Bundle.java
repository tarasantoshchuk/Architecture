package com.tarasantoshchuk.arch.core.routing;

import com.tarasantoshchuk.arch.core.routing.bundle_impl.EmptyBundle;

import java.io.Serializable;

public interface Bundle {
    Bundle EMPTY = new EmptyBundle();

    boolean isEmpty();
    void clear();

    void putString(String key, String value);
    void putInt(String key, int value);
    void putSerializable(String key, Serializable serializable);

    default String getString(String key) {
        return getString(key, null);
    }

    default int getInt(String key) {
        return getInt(key, 0);
    }

    String getString(String key, String defaultValue);
    int getInt(String key, int value);
    Serializable getSerializable(String key);
}
