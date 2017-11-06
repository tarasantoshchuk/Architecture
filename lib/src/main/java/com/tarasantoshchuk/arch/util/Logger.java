package com.tarasantoshchuk.arch.util;


import android.util.Log;

public class Logger {
    public static void v(Object o, String message) {
        Log.v(tag(o), message);
    }

    private static String tag(Object o) {
        return o.getClass().getSimpleName() + "@" + System.identityHashCode(o);
    }

    private static String tag(Class<?> c) {
        return c.getSimpleName();
    }

    public static void e(Object o, String message, Throwable e) {
        Log.e(tag(o), message, e);
    }
}
