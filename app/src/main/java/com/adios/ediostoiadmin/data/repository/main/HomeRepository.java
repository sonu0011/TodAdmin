package com.adios.ediostoiadmin.data.repository.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adios.ediostoiadmin.data.modal.payloads.LoginPayload;
import com.adios.ediostoiadmin.data.modal.payloads.OrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;
import com.adios.ediostoiadmin.data.network.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static final String TAG = "HomeRepository";

    public LiveData<AllOrdersResponse> getAllOrders(OrderPayload orderPayload) {
        final MutableLiveData<AllOrdersResponse> allorders = new MutableLiveData<>();
        ToiAdminApi.getInstance().getAllNetworkRequests()
                .getAllOrders(orderPayload)
                .enqueue(new Callback<AllOrdersResponse>() {
                    @Override
                    public void onResponse(Call<AllOrdersResponse> call, Response<AllOrdersResponse> response) {
                        allorders.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<AllOrdersResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        allorders.postValue(null);
                    }
                });
        return allorders;

    }

}
