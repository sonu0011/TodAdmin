package com.adios.ediostoiadmin.data.repository.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adios.ediostoiadmin.data.network.responses.AuthResponse;
import com.adios.ediostoiadmin.data.modal.payloads.LoginPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static final String TAG = "UserRepository";

    public LiveData<AuthResponse> loginUser(final LoginPayload loginPayload) {
        final MutableLiveData<AuthResponse> loginResponse = new MutableLiveData<>();
        ToiAdminApi.getInstance().getAllNetworkRequests()
                .userLogin(loginPayload)
                .enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        loginResponse.postValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        loginResponse.postValue(null);
                    }
                });
            return loginResponse;

    }
}
