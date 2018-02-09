package com.theapache64.github_android_sdk.requests;

import com.google.gson.annotations.SerializedName;

/**
 * Created by theapache64 on 9/2/18.
 */

public class ListIssuesRequest {

    @SerializedName("state")
    private final String state;

    public ListIssuesRequest(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
