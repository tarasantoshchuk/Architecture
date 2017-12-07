package com.tarasantoshchuk.arch.util.log;

import android.util.Log;

class DefaultLogger {
    static final ILogger INSTANCE = new ILogger() {
        @Override
        public void log(String tag, String message) {
            Log.v(tag, message);
        }

        @Override
        public void logError(String tag, String message, Throwable error) {
            Log.e(tag, message, error);
        }
    };
}
