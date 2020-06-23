package com.adios.ediostoiadmin.viewmodels;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.adios.ediostoiadmin.data.modal.payloads.LoginPayload;
import com.adios.ediostoiadmin.data.network.responses.AuthResponse;
import com.adios.ediostoiadmin.data.repository.auth.UserRepository;
import com.adios.ediostoiadmin.util.AuthListener;

public class AuthViewModel extends ViewModel {
    public String username = "";
    public String password = "";
    public AuthListener authListener;
    private Activity context;

    public void onLoginButtonClick(View view) {
        authListener.onStarted();
        if (username.trim().isEmpty()) {
            authListener.onFailure("User Name can't be empty");
            return;
        } else if (password.trim().isEmpty()) {
            authListener.onFailure("Password  can't be empty");
            return;
        }
        UserRepository userRepository = new UserRepository();
        LiveData<AuthResponse> response = userRepository.loginUser(new LoginPayload(username, password));
        authListener.onSuccess(response);
    }
}
