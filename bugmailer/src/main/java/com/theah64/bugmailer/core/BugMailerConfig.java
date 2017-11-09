package com.theah64.bugmailer.core;

import com.theah64.bugmailer.utils.DarKnight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BugMailerConfig {

    private static final String DEFAULT_UNAME = "7Ff7IxIAqmmz6TlA6GntsvbUSs+CG7UW610/nJ+IKso=";
    private static final String DEFAULT_PWRD = "UMmyvG2Bp8ZCCKaV6r+CHhJ8T4GJhUN/941H9RG8SUQ=";

    private final String emailUsername, emailPassword;
    private String themeColor;
    private final List<String> recipients = new ArrayList<>();

    public BugMailerConfig(String emailUsername, String emailPassword, String primaryRecipient) {
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
        recipients.add(primaryRecipient);
    }

    public BugMailerConfig(String primaryRecipient) {
        this(DarKnight.getDecrypted(DEFAULT_UNAME), DarKnight.getDecrypted(DEFAULT_PWRD), primaryRecipient);
    }


    public BugMailerConfig setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public BugMailerConfig addRepientEmail(String email) {
        recipients.add(email);
        return this;
    }


    public String getEmailUsername() {
        return emailUsername;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public List<String> getRecipients() {
        return recipients;
    }
}
