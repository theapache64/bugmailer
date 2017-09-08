package com.theah64.bugmailerexample;

import android.app.Application;

import com.theah64.bugmailer.BugMailer;


/**
 * Created by theapache64 on 7/9/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BugMailer.init("mymailer64@gmail.com", "mypassword64", "theapache64@gmail.com");
    }
}
