package com.tarasantoshchuk.arch.sample.core.routing;

import com.tarasantoshchuk.arch.core.routing.ScreensResolver.Screen;
import com.tarasantoshchuk.arch.sample.features.edit.EditActivity;
import com.tarasantoshchuk.arch.sample.features.main.MainActivity;

public enum Screens implements Screen {
    MAIN(MainActivity.class),
    EDIT(EditActivity.class),
    TOASTER(null);

    Class<?> mScreenClass;

    Screens(Class<?> screenClass) {
        mScreenClass = screenClass;
    }

    public Class<?> toClass() {
        return mScreenClass;
    }

    public int toRequestCode() {
        return ordinal();
    }

    public static Screens fromCode(int code) {
        return Screens.values()[code];
    }
}
