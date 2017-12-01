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

    String getString(String key);

    int getInt(String key);

    String getString(String key, String defaultValue);
    int getInt(String key, int value);
    Serializable getSerializable(String key);
}
