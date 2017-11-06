package com.tarasantoshchuk.arch.core.routing;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tarasantoshchuk.arch.core.routing.bundle_impl.BaseBundle;
import com.tarasantoshchuk.arch.core.routing.bundle_impl.BundleImpl;

public abstract class BundleConverter {
    @NonNull
    public static Bundle fromAndroidBundle(@Nullable android.os.Bundle androidBundle) {
        return new BundleImpl(androidBundle != null ? androidBundle : android.os.Bundle.EMPTY);
    }

    @NonNull
    public static Bundle fromIntent(@Nullable Intent intent) {
        return fromAndroidBundle(intent != null ? intent.getExtras() : null);
    }

    @NonNull
    public static android.os.Bundle toAndroidBundle(@Nullable Bundle bundle) {
        if (bundle != null) {
            return ((BaseBundle) bundle).toAndroidBundle();
        } else {
            return android.os.Bundle.EMPTY;
        }
    }
}
