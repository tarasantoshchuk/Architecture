package com.tarasantoshchuk.arch.core.routing;


import com.tarasantoshchuk.arch.core.routing.bundle_impl.BundleImpl;

public abstract class BundleConverter {
    public static Bundle fromAndroidBundle(android.os.Bundle androidBundle) {
        return new BundleImpl(androidBundle);
    }

    public static android.os.Bundle toAndroidBundle(Bundle bundle) {
        return ((BundleImpl) bundle).toAndroidBundle();
    }
}
