package com.theah64.bugmailer.core;

/**
 * Created by theapache64 on 8/2/18.
 */

public class GitHubCommentGenerator {
    private final StringBuilder builder = new StringBuilder();

    public GitHubCommentGenerator addNode(String key, String value) {
        builder.append("**").append(key).append("**").append("\n").append(value).append("\n\n");
        return this;
    }


    public GitHubCommentGenerator addNode(String key, int value) {
        return addNode(key, String.valueOf(value));
    }

    public GitHubCommentGenerator addCodeNode(String key, String value) {
        builder.append("**").append(key).append("**").append("\n```java\n").append(value).append("```\n\n");
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
