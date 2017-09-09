package com.theah64.bugmailer.models;

/**
 * Created by theapache64 on 9/9/17.
 */

public class Node {

    private static final String KEY_HEADER = "<div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">";
    private static final String KEY_FOOTER = "</div> <div style=\"padding:0px 0px 0px 24px\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div>";
    private static final String VALUE_FOOTER = "</div> </td> </tr> </tbody> </table> </div> </div>";

    private final StringBuilder report;

    public Node(final String key, final String value) {
        report = new StringBuilder(KEY_HEADER)
                .append(key)
                .append(KEY_FOOTER)
                .append(value)
                .append(VALUE_FOOTER);
    }

    public Node(String key, int sdkInt) {
        this(key, String.valueOf(sdkInt));
    }


    @Override
    public String toString() {
        return report.toString();
    }
}