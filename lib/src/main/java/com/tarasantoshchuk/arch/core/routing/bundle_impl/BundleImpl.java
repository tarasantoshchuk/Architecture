package com.tarasantoshchuk.arch.core.routing.bundle_impl;


import java.io.Serializable;

public final class BundleImpl extends BaseBundle {
    private android.os.Bundle mInnerBundle;

    public BundleImpl(android.os.Bundle bundle) {
        mInnerBundle = bundle;
    }

    @Override
    public boolean isEmpty() {
        return mInnerBundle.isEmpty();
    }

    @Override
    public void clear() {
        mInnerBundle.clear();
    }

    @Override
    public void putString(String key, String value) {
        mInnerBundle.putString(key, value);
    }

    @Override
    public void putInt(String key, int value) {
        mInnerBundle.putInt(key, value);
    }

    @Override
    public void putSerializable(String key, Serializable serializable) {
        mInnerBundle.putSerializable(key, serializable);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return mInnerBundle.getString(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return mInnerBundle.getInt(key, defaultValue);
    }

    @Override
    public Serializable getSerializable(String key) {
        return mInnerBundle.getSerializable(key);
    }

    public android.os.Bundle toAndroidBundle() {
        return mInnerBundle;
    }
}
