package com.codezync.codezync_boilerplate.Listeners;


import com.codezync.codezync_boilerplate.model.google_direction.GoogleDirectionResponse;

public interface OnDirectionFetchedListener {
    void onResult(GoogleDirectionResponse result);
}
