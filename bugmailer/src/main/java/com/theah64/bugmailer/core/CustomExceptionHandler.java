package com.theah64.bugmailer.core;

import android.util.Log;

/**
 * Created by theapache64 on 9/9/17.
 */

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final String X = CustomExceptionHandler.class.getSimpleName();
    private Thread.UncaughtExceptionHandler defaultUEH;

    public CustomExceptionHandler() {
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }


    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d(X, "Caught uncaught exception");
        BugMailer.report(e);
        defaultUEH.uncaughtException(t, e);
    }
}
