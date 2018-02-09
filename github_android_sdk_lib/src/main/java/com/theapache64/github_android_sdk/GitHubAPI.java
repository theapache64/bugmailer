package com.theapache64.github_android_sdk;

import com.theapache64.github_android_sdk.requests.CreateCommentRequest;
import com.theapache64.github_android_sdk.requests.CreateIssueRequest;
import com.theapache64.github_android_sdk.responses.CreateCommentResponse;
import com.theapache64.github_android_sdk.responses.CreateIssueResponse;
import com.theapache64.github_android_sdk.responses.ListIssuesResponse;
import com.theapache64.github_android_sdk.utils.CallbackManager;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by theapache64 on 8/2/18.
 */

public class GitHubAPI {

    private static String accessToken;
    private static APIInterface client;

    public static void init(String accessToken) {

        //Initializing access token
        GitHubAPI.accessToken = "token " + accessToken;


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        client = retrofit.create(APIInterface.class);
    }

    public static String getAccessToken() {
        return accessToken;
    }


    public static void createIssue(String owner, String repo, String title, String body, final Callback<CreateIssueResponse> callback) {
        client.createIssue(accessToken, owner, repo, new CreateIssueRequest(body, title, null, 0, null)).enqueue(new CallbackManager<CreateIssueResponse>(callback));
    }

    public static void createComment(String owner, String repo, int number, String body, final Callback<CreateCommentResponse> callback) {
        client.createComment(accessToken, owner, repo, number, new CreateCommentRequest(body)).enqueue(new CallbackManager<CreateCommentResponse>(callback));
    }

    public static void listIssues(String owner, String repo, IssueType issueType, final Callback<List<ListIssuesResponse.Issue>> callback) {
        client.listIssues(accessToken, owner, repo, issueType.getKey()).enqueue(new CallbackManager<>(callback));
    }

    public enum IssueType {

        ALL("all"), OPEN("open"), CLOSED("closed");

        private final String key;

        IssueType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }


    public interface Callback<R> {
        void onSuccess(R r);

        void onError(Throwable t);
    }
}
