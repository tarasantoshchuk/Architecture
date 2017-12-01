package com.tarasantoshchuk.arch.core.routing.bundle_impl;


import com.tarasantoshchuk.arch.core.routing.Bundle;

public abstract class BaseBundle implements Bundle {
    public abstract android.os.Bundle toAndroidBundle();

    @Override
    public final String getString(String key) {
        return getString(key, null);
    }

    @Override
    public final int getInt(String key) {
        return getInt(key, 0);
    }
}
