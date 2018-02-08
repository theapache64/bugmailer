package com.theapache64.github_android_sdk.responses;

import com.google.gson.annotations.SerializedName;


/**
 * Generated using MockAPI (https://github.com/theapache64/Mock-API) : Thu Feb 08 16:59:38 IST 2018
 */
public class CreateCommentResponse {

    @SerializedName("id")
    private final int id;

    @SerializedName("url")
    private final String url;

    @SerializedName("body")
    private final String body;

    @SerializedName("user")
    private final User user;

    @SerializedName("html_url")
    private final String htmlUrl;

    @SerializedName("issue_url")
    private final String issueUrl;

    @SerializedName("updated_at")
    private final String updatedAt;

    @SerializedName("created_at")
    private final String createdAt;

    @SerializedName("author_association")
    private final String authorAssociation;

    @SerializedName("message")
    private final String message;


    public CreateCommentResponse(int id, String url, String body, User user, String htmlUrl, String issueUrl, String updatedAt, String createdAt, String authorAssociation, String message) {
        this.id = id;
        this.url = url;
        this.body = body;
        this.user = user;
        this.htmlUrl = htmlUrl;
        this.issueUrl = issueUrl;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.authorAssociation = authorAssociation;
        this.message = message;
    }

    public String getMessage() {
        return message;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
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


}