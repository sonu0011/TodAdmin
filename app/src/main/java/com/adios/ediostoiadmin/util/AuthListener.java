package com.adios.ediostoiadmin.util;

import androidx.lifecycle.LiveData;

import com.adios.ediostoiadmin.data.network.responses.AuthResponse;

public interface AuthListener {
    void onStarted();

    void onSuccess(LiveData<AuthResponse> loginResponse);

    void onFailure(String message);
}
