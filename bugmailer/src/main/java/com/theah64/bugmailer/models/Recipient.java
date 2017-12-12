package com.theah64.bugmailer.models;

/**
 * Created by theapache64 on 12/12/17.
 */

public class Recipient {

    private final String email;
    private final Class<? extends Throwable> exceptionClass;

    public Recipient(String email, Class<? extends Throwable> exceptionClass) {
        this.email = email;
        this.exceptionClass = exceptionClass;
    }

    public String getEmail() {
        return email;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return exceptionClass;
    }
}
