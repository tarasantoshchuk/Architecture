package com.tarasantoshchuk.arch.core.routing.bundle_impl;


import com.tarasantoshchuk.arch.core.routing.Bundle;

public abstract class BaseBundle implements Bundle {
    abstract android.os.Bundle toAndroidBundle();
}
