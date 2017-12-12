package com.theah64.bugmailer.core;

import com.theah64.bugmailer.models.Recipient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BugMailerConfig {

    private String themeColor;
    private final List<Recipient> recipients = new ArrayList<>();

    public BugMailerConfig(String primaryRecipient) {
        addRecipientEmail(primaryRecipient);
    }


    public BugMailerConfig setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public BugMailerConfig addRecipientEmail(String email) {
        return addRecipientEmail(email, Throwable.class);
    }


    public String getThemeColor() {
        return themeColor;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public BugMailerConfig addRecipientEmail(String email, Class<? extends Throwable> exceptionToBeReported) {
        recipients.add(new Recipient(email, exceptionToBeReported));
        return this;
    }
}
