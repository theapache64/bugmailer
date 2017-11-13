package com.theah64.bugmailer.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BugMailerConfig {

    private String themeColor;
    private final List<String> recipients = new ArrayList<>();

    public BugMailerConfig(String primaryRecipient) {
        recipients.add(primaryRecipient);
    }


    public BugMailerConfig setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public BugMailerConfig addRecipientEmail(String email) {
        recipients.add(email);
        return this;
    }


    public String getThemeColor() {
        return themeColor;
    }

    public List<String> getRecipients() {
        return recipients;
    }
}
