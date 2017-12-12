package com.theah64.bugmailerexample;

import android.app.Application;

import com.theah64.bugmailer.core.BugMailer;
import com.theah64.bugmailer.core.BugMailerConfig;
import com.theah64.bugmailer.core.Colors;
import com.theah64.bugmailer.exceptions.BugMailerException;

import org.json.JSONException;


/**
 * Created by theapache64 on 7/9/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        try {
            BugMailer.init(this,
                    new BugMailerConfig("theapache64@gmail.com")
                            .addRecipientEmail("anoojns@gmail.com", JSONException.class)
                            .setThemeColor(Colors.MATERIAL_AMBER_GREEN_500)
            );
        } catch (BugMailerException e) {
            e.printStackTrace();
        }


    }
}
