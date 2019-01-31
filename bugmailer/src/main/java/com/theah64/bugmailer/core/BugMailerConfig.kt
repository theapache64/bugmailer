package com.theah64.bugmailer.core

import com.theah64.bugmailer.models.Recipient

import java.util.ArrayList

/**
 * Created by theapache64 on 9/9/17.
 */

class BugMailerConfig(primaryRecipient: String) {

    var themeColor: String? = null
    internal val recipients = ArrayList<Recipient>()
    var owner: String? = null
        private set
    var repo: String? = null
        private set

    val isGitHubIssueTracker: Boolean
        get() = owner != null && repo != null

    init {
        addRecipientEmail(primaryRecipient)
    }


    fun setThemeColor(themeColor: String): BugMailerConfig {
        this.themeColor = themeColor
        return this
    }


    fun getRecipients(): List<Recipient> {
        return recipients
    }

    @JvmOverloads
    fun addRecipientEmail(email: String, exceptionToBeReported: Class<out Throwable> = Throwable::class.java): BugMailerConfig {
        recipients.add(Recipient(email, exceptionToBeReported))
        return this
    }

    fun enableGitHubIssueTracker(owner: String, repo: String): BugMailerConfig {
        this.owner = owner
        this.repo = repo
        return this
    }
}
