package com.theah64.bugmailer.core

import android.util.Log

/**
 * Created by theapache64 on 9/9/17.
 */

class CustomExceptionHandler : Thread.UncaughtExceptionHandler {
    private val defaultUEH: Thread.UncaughtExceptionHandler

    init {
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler()
    }


    override fun uncaughtException(t: Thread, e: Throwable) {

        Log.d(X, "Caught uncaught exception")
        BugMailer.report(e)
        defaultUEH.uncaughtException(t, e)
    }

    companion object {

        private val X = CustomExceptionHandler::class.java.simpleName
    }
}
