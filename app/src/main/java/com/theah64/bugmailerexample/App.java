package com.theah64.bugmailerexample;

import android.app.Application;

import com.theah64.bugmailer.core.BugMailer;
import com.theah64.bugmailer.core.BugMailerConfig;
import com.theah64.bugmailer.core.Colors;
import com.theah64.bugmailer.exceptions.BugMailerException;


/**
 * Created by theapache64 on 7/9/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        final BugMailerConfig config = new BugMailerConfig(this, "theapache64@gmail.com")
                .setThemeColor(Colors.MATERIAL_DEEP_BLUE_500);

        try {
            BugMailer.init(config);
        } catch (BugMailerException e) {
            e.printStackTrace();
        }


    }
}
