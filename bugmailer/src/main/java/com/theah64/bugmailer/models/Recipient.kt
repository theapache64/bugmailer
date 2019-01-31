package com.theah64.bugmailer.models

/**
 * Created by theapache64 on 12/12/17.
 */

class Recipient(val email: String, val exceptionClass: Class<out Throwable>)
