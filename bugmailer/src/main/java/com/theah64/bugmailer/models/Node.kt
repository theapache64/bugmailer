package com.theah64.bugmailer.models


/**
 * Created by theapache64 on 9/9/17.
 */

open class Node(val key: String, val value: String) {

    private val report: StringBuilder

    init {
        report = StringBuilder(KEY_HEADER)
                .append(key)
                .append(KEY_FOOTER)
                .append(value.replace("\n".toRegex(), "<br>"))
                .append(VALUE_FOOTER)
    }

    constructor(key: String, sdkInt: Int) : this(key, sdkInt.toString()) {}


    override fun toString(): String {
        return report.toString()
    }

    companion object {

        private val KEY_HEADER = "<div style=\"padding:22px 24px 0px 0px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"font-size:14px;line-height:20px;font-family:roboto,sans-serif;font-weight:400;color:#757575;padding:0px 0px 0px 24px\">"
        private val KEY_FOOTER = "</div> <div style=\"padding:0px 0px 0px 24px\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400;font-size:14px\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div>"
        private val VALUE_FOOTER = "</div> </td> </tr> </tbody> </table> </div> </div>"
    }
}