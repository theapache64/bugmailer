package com.theah64.bugmailerexample;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.theah64.bugmailer.core.BugMailer;
import com.theah64.bugmailer.core.BugMailerConfig;
import com.theah64.bugmailer.core.Colors;
import com.theah64.bugmailer.exceptions.BugMailerException;

import org.json.JSONException;


/**
 * Created by theapache64 on 7/9/17.
 */

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        try {
            BugMailer.init(this,
                    new BugMailerConfig("theapache64@gmail.com")
                            .addRecipientEmail("anoojns@gmail.com", JSONException.class)
                            .setThemeColor(Colors.MATERIAL_RED_500)
            );

        } catch (BugMailerException e) {
            e.printStackTrace();
        }


    }
}
