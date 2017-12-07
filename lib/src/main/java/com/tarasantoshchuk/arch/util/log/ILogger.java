package com.tarasantoshchuk.arch.util.log;

public interface ILogger {
    void log(String tag, String message);
    void logError(String tag, String message, Throwable error);
}
