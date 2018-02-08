package com.theapache64.github_android_sdk.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by theapache64 on 8/2/18.
 */

public class CreateIssueResponse {


    @SerializedName("id")
    private final int id;

    @SerializedName("url")
    private final String url;

    @SerializedName("body")
    private final String body;

    @SerializedName("user")
    private final User user;

    @SerializedName("title")
    private final String title;

    @SerializedName("state")
    private final String state;

    @SerializedName("labels")
    private final List<Label> labels;

    @SerializedName("number")
    private final int number;

    @SerializedName("locked")
    private final boolean locked;

    @SerializedName("comments")
    private final int comments;

    @SerializedName("html_url")
    private final String htmlUrl;

    @SerializedName("assignee")
    private final String assignee;

    @SerializedName("closed_at")
    private final String closedAt;

    @SerializedName("assignees")
    private final List<Assignee> assignees;

    @SerializedName("closed_by")
    private final String closedBy;

    @SerializedName("milestone")
    private final String milestone;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName("labels_url")
    private final String labelsUrl;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("events_url")
    private final String eventsUrl;

    @SerializedName("comments_url")
    private final String commentsUrl;

    @SerializedName("repository_url")
    private final String repositoryUrl;

    @SerializedName("author_association")
    private final String authorAssociation;


    public CreateIssueResponse(int id, String url, String body, User user, String title, String state, List<Label> labels, int number, boolean locked, int comments, String htmlUrl, String assignee, String closedAt, List<Assignee> assignees, String closedBy, String milestone, String createdAt, String labelsUrl, String updatedAt, String eventsUrl, String commentsUrl, String repositoryUrl, String authorAssociation) {
        this.id = id;
        this.url = url;
        this.body = body;
        this.user = user;
        this.title = title;
        this.state = state;
        this.labels = labels;
        this.number = number;
        this.locked = locked;
        this.comments = comments;
        this.htmlUrl = htmlUrl;
        this.assignee = assignee;
        this.closedAt = closedAt;
        this.assignees = assignees;
        this.closedBy = closedBy;
        this.milestone = milestone;
        this.createdAt = createdAt;
        this.labelsUrl = labelsUrl;
        this.updatedAt = updatedAt;
        this.eventsUrl = eventsUrl;
        this.commentsUrl = commentsUrl;
        this.repositoryUrl = repositoryUrl;
        this.authorAssociation = authorAssociation;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public int getNumber() {
        return number;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getComments() {
        return comments;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public String getMilestone() {
        return milestone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public static class User {

        @SerializedName("id")
        private final int id;

        @SerializedName("url")
        private final String url;

        @SerializedName("type")
        private final String type;

        @SerializedName("login")
        private final String login;

        @SerializedName("html_url")
        private final String htmlUrl;

        @SerializedName("gists_url")
        private final String gistsUrl;

        @SerializedName("repos_url")
        private final String reposUrl;

        @SerializedName("avatar_url")
        private final String avatarUrl;

        @SerializedName("events_url")
        private final String eventsUrl;

        @SerializedName("site_admin")
        private final boolean siteAdmin;

        @SerializedName("starred_url")
        private final String starredUrl;

        @SerializedName("gravatar_id")
        private final String gravatarId;

        @SerializedName("following_url")
        private final String followingUrl;

        @SerializedName("followers_url")
        private final String followersUrl;

        @SerializedName("subscriptions_url")
        private final String subscriptionsUrl;

        @SerializedName("organizations_url")
        private final String organizationsUrl;

        @SerializedName("received_events_url")
        private final String receivedEventsUrl;


        public User(int id, String url, String type, String login, String htmlUrl, String gistsUrl, String reposUrl, String avatarUrl, String eventsUrl, boolean siteAdmin, String starredUrl, String gravatarId, String followingUrl, String followersUrl, String subscriptionsUrl, String organizationsUrl, String receivedEventsUrl) {
            this.id = id;
            this.url = url;
            this.type = type;
            this.login = login;
            this.htmlUrl = htmlUrl;
            this.gistsUrl = gistsUrl;
            this.reposUrl = reposUrl;
            this.avatarUrl = avatarUrl;
            this.eventsUrl = eventsUrl;
            this.siteAdmin = siteAdmin;
            this.starredUrl = starredUrl;
            this.gravatarId = gravatarId;
            this.followingUrl = followingUrl;
            this.followersUrl = followersUrl;
            this.subscriptionsUrl = subscriptionsUrl;
            this.organizationsUrl = organizationsUrl;
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public int getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public String getType() {
            return type;
        }

        public String getLogin() {
            return login;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public String getGistsUrl() {
            return gistsUrl;
        }

        public String getReposUrl() {
            return reposUrl;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public String getEventsUrl() {
            return eventsUrl;
        }

        public boolean isSiteAdmin() {
            return siteAdmin;
        }

        public String getStarredUrl() {
            return starredUrl;
        }

        public String getGravatarId() {
            return gravatarId;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

    }

    public static class Label {


        public Label() {
        }

    }

    public static class Assignee {


        public Assignee() {
        }

    }
}
