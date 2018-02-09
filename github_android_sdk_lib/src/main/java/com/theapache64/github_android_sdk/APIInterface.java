package com.theapache64.github_android_sdk;

import com.theapache64.github_android_sdk.requests.CreateCommentRequest;
import com.theapache64.github_android_sdk.requests.CreateIssueRequest;
import com.theapache64.github_android_sdk.responses.CreateCommentResponse;
import com.theapache64.github_android_sdk.responses.CreateIssueResponse;
import com.theapache64.github_android_sdk.responses.ListIssuesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by theapache64 on 8/2/18.
 */

public interface APIInterface {

    @POST("repos/{owner}/{repo}/issues")
    Call<CreateIssueResponse> createIssue(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Body CreateIssueRequest createIssueRequest
    );


    @GET("repos/{owner}/{repo}/issues")
    Call<List<ListIssuesResponse.Issue>> listIssues(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Query("state") String state
    );

    @POST("repos/{owner}/{repo}/issues/{number}/comments")
    Call<CreateCommentResponse> createComment(
            @Header("Authorization") String accessToken,
            @Path("owner") String owner,
            @Path("repo") String repo,
            @Path("number") int number,
            @Body CreateCommentRequest createCommentRequest
    );


}
