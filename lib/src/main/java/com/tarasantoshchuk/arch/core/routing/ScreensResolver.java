package com.tarasantoshchuk.arch.core.routing;

public abstract class ScreensResolver<T extends ScreensResolver.Screen> {
    private static ScreensResolver sInstance;

    public static void install(ScreensResolver resolver) {
        sInstance = resolver;
    }

    protected abstract int toRequestCode(T screen);
    protected abstract T fromRequestCode(int requestCode);
    protected abstract Class<?> toScreenClass(T screen);

    @SuppressWarnings("unchecked")
    public static int requestCode(Screen screen) {
        return sInstance.toRequestCode(screen);
    }

    public static Screen screen(int requestCode) {
        return sInstance.fromRequestCode(requestCode);
    }

    @SuppressWarnings("unchecked")
    static Class<?> screenClass(Screen screen) {
        return sInstance.toScreenClass(screen);
    }

    public interface Screen {
    }
}
