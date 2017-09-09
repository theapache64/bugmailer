package com.theah64.bugmailer.core;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 9/9/17.
 */

public class BugMailerConfig {

    private final Context context;
    private final String emailUsername, emailPassword;
    private String themeColor;
    private final List<String> recipients = new ArrayList<>();
    private boolean reportDeliveryToast = false;

    public BugMailerConfig(Context context, String emailUsername, String emailPassword, String primaryRecipient) {
        this.context = context;
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
        recipients.add(primaryRecipient);
    }


    public BugMailerConfig setThemeColor(String themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public BugMailerConfig addRepientEmail(String email) {
        recipients.add(email);
        return this;
    }

    public BugMailerConfig setReportDeliveryToast(boolean reportDeliveryToast) {
        this.reportDeliveryToast = reportDeliveryToast;
        return this;
    }

    public boolean isReportDeliveryToast() {
        return reportDeliveryToast;
    }

    public Context getContext() {
        return context;
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
