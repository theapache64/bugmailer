package com.theah64.bugmailer.utils

import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by theapache64 on 9/9/17.
 */

object CommonUtils {
    fun getStackTrace(e: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        return sw.toString()
    }
}
