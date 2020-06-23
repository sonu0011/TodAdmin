package com.adios.ediostoiadmin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.AllOrdersAdapter;
import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.data.modal.payloads.SearchOrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = "SearchResultActivity";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    SearchOrderPayload payload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        getSupportActionBar().setTitle("Searched Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.search_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.search_progressbar);
        payload = getIntent().getParcelableExtra("searchPayload");


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        ToiAdminApi.getInstance().getAllNetworkRequests()
                .searchOrders(payload)
                .enqueue(new Callback<AllOrdersResponse>() {
                    @Override
                    public void onResponse(Call<AllOrdersResponse> call, Response<AllOrdersResponse> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().getResult_Output().getCustomerOrders().size() == 0) {
                                Toast.makeText(SearchResultActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            recyclerView.setAdapter(new AllOrdersAdapter(response.body().getResult_Output().getCustomerOrders(), SearchResultActivity.this, 0));
                        }
                    }

                    @Override
                    public void onFailure(Call<AllOrdersResponse> call, Throwable t) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }


}
