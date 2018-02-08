package com.theapache64.github_android_sdk;

/**
 * Created by theapache64 on 8/2/18.
 */

public interface Callback<RESPONSE> {
    void onSuccess(RESPONSE r);

    void onError(Throwable t);
}
