package com.theapache64.github_android_sdk.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by theapache64 on 8/2/18.
 */

public class CreateIssueRequest {

    @SerializedName("body")
    private final String body;

    @SerializedName("title")
    private final String title;

    @SerializedName("labels")
    private final List<String> labels;

    @SerializedName("milestone")
    private final int milestone;

    @SerializedName("assignees")
    private final List<String> assignees;


    public CreateIssueRequest(String body, String title, List<String> labels, int milestone, List<String> assignees) {
        this.body = body;
        this.title = title;
        this.labels = labels;
        this.milestone = milestone;
        this.assignees = assignees;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLabels() {
        return labels;
    }

    public int getMilestone() {
        return milestone;
    }

    public List<String> getAssignees() {
        return assignees;
    }


}
