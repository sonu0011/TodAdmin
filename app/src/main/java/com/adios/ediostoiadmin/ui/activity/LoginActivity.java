package com.adios.ediostoiadmin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.network.responses.AuthResponse;
import com.adios.ediostoiadmin.databinding.ActivityLoginBinding;
import com.adios.ediostoiadmin.util.AuthListener;
import com.adios.ediostoiadmin.viewmodels.AuthViewModel;

public class LoginActivity extends AppCompatActivity implements AuthListener {
    ActivityLoginBinding binding;
    SharedPrefrenceManager manager = new SharedPrefrenceManager(LoginActivity.this);

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        isUserLoggedIn();
        AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.setAuthViewModel(authViewModel);
        authViewModel.authListener = this;
    }

    private void isUserLoggedIn() {
        if (!manager.getUserName(this).equals("")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onStarted() {
        binding.loginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(LiveData<AuthResponse> response) {
        response.observe(this, new Observer<AuthResponse>() {
            @Override
            public void onChanged(AuthResponse authResponse) {
                binding.loginProgressBar.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "onChanged: "+authResponse.toString());
                if (authResponse.getResult_Status().equalsIgnoreCase("Failed")) {
                    Toast.makeText(LoginActivity.this, "Invalid User Name or Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (authResponse.getResult_Status().equalsIgnoreCase("Success")) {
                    Toast.makeText(LoginActivity.this, "User Authenticated Successfully", Toast.LENGTH_SHORT).show();
                    manager.storeUsernameAndPassword(
                            binding.loginEmailAddress.getEditText().getText().toString().trim(),
                            binding.loginPassword.getEditText().getText().toString().trim()
                    );
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    finish();
                }

            }
        });

    }


    @Override
    public void onFailure(String message) {
        binding.loginProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
