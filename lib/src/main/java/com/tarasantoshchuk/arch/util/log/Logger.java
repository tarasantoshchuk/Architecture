package com.tarasantoshchuk.arch.util.log;


public class Logger {
    private static ILogger sLoggerImpl = DefaultLogger.INSTANCE;

    public static void setLogger(ILogger logger) {
        sLoggerImpl = logger;
    }

    public static void v(Object o, String message) {
        sLoggerImpl.log(tag(o), message);
    }

    private static String tag(Object o) {
        return o.getClass().getSimpleName() + "@" + System.identityHashCode(o);
    }

    public static void e(Object o, String message, Throwable e) {
        sLoggerImpl.logError(tag(o), message, e);
    }
}
