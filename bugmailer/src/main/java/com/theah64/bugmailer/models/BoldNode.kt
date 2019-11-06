package com.theah64.bugmailer.models

/**
 * Created by theapache64 on 9/9/17.
 */

class BoldNode(key: String, value: String) : Node(key, String.format("<b>%s</b>", value))
