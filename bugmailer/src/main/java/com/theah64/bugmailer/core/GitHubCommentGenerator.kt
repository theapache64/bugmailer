package com.theah64.bugmailer.core

/**
 * Created by theapache64 on 8/2/18.
 */

class GitHubCommentGenerator {
    private val builder = StringBuilder()

    fun addNode(key: String, value: String): GitHubCommentGenerator {
        builder.append("**").append(key).append("**").append("\n").append(value).append("\n\n")
        return this
    }


    fun addNode(key: String, value: Int): GitHubCommentGenerator {
        return addNode(key, value.toString())
    }

    fun addCodeNode(key: String, value: String): GitHubCommentGenerator {
        builder.append("**").append(key).append("**").append("\n```java\n").append(value).append("```\n\n")
        return this
    }

    override fun toString(): String {
        return builder.toString()
    }
}
