package com.theah64.bugmailer.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by theapache64 on 9/9/17.
 */

public class CommonUtils {
    public static String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
