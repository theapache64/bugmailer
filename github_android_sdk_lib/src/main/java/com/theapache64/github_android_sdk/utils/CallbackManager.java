package com.theapache64.github_android_sdk.utils;

import android.support.annotation.NonNull;

import com.theapache64.github_android_sdk.GitHubAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by theapache64 on 8/2/18.
 */

public class CallbackManager<RESPONSE> implements Callback<RESPONSE> {

    private final GitHubAPI.Callback<RESPONSE> callback;

    public CallbackManager(GitHubAPI.Callback<RESPONSE> callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(@NonNull Call<RESPONSE> call, @NonNull Response<RESPONSE> response) {
        if (response.body() != null) {
            callback.onSuccess(response.body());
        } else {
            try {
                callback.onError(new Throwable(new JSONObject(response.errorBody().string()).getString("message")));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<RESPONSE> call, Throwable t) {
        t.printStackTrace();
        callback.onError(t);
    }
}
