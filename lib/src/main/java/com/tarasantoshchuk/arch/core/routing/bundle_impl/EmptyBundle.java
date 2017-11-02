package com.tarasantoshchuk.arch.core.routing.bundle_impl;


import android.os.Bundle;

import java.io.Serializable;

public final class EmptyBundle extends BaseBundle {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void clear() {
    }

    @Override
    public void putString(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putInt(String key, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putSerializable(String key, Serializable serializable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return defaultValue;
    }

    @Override
    public int getInt(String key, int value) {
        return value;
    }

    @Override
    public Serializable getSerializable(String key) {
        return null;
    }

    @Override
    Bundle toAndroidBundle() {
        return Bundle.EMPTY;
    }
}
