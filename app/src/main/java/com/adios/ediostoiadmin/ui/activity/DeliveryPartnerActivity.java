package com.adios.ediostoiadmin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.DeliveryPartnerAdapter;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.payloads.DeliveryPartnerPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.DeliveryPartnerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryPartnerActivity extends AppCompatActivity {
    private static final String TAG = "DeliveryPartnerActivity";
    SharedPrefrenceManager manager = new SharedPrefrenceManager(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_partner);
        getSupportActionBar().setTitle("Assign Delivery Partner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final RecyclerView recyclerView = findViewById(R.id.dp_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ToiAdminApi.getInstance().getAllNetworkRequests()
                .getDeliveryPartners(new DeliveryPartnerPayload(manager.getUserName(DeliveryPartnerActivity.this)))
                .enqueue(new Callback<DeliveryPartnerResponse>() {
                    @Override
                    public void onResponse(Call<DeliveryPartnerResponse> call, Response<DeliveryPartnerResponse> response) {
                        Log.d(TAG, "onResponse: " + response.body().toString());

                        if (response.body().getResult_OutputObject().getDeliveryPartners().size() == 0) {
                            findViewById(R.id.no_dp).setVisibility(View.VISIBLE);
                        } else {
                            findViewById(R.id.no_dp).setVisibility(View.INVISIBLE);

                            DeliveryPartnerAdapter adapter = new DeliveryPartnerAdapter(response.body().getResult_OutputObject().getDeliveryPartners(), DeliveryPartnerActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<DeliveryPartnerResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
