package com.theapache64.github_android_sdk.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by theapache64 on 8/2/18.
 */

public class CreateCommentRequest {

    @SerializedName("body")
    private final String body;

    public CreateCommentRequest(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
