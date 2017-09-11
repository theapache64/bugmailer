package com.theah64.bugmailerexample;

import android.app.Application;

import com.theah64.bugmailer.core.BugMailer;
import com.theah64.bugmailer.core.BugMailerConfig;
import com.theah64.bugmailer.exceptions.BugMailerException;


/**
 * Created by theapache64 on 7/9/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        try {
            BugMailer.init(this, new BugMailerConfig("yoursender@gmail.com", "yourpassword", "theapache64@gmail.com"));
        } catch (BugMailerException e) {
            e.printStackTrace();
        }


    }
}
