package com.adios.ediostoiadmin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.AllOrdersAdapter;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.data.modal.payloads.OrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;
import com.adios.ediostoiadmin.util.Constants;
import com.adios.ediostoiadmin.util.UtilityMethods;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingFragment extends Fragment {
    private View view;
    private AllOrdersAdapter adapter;
    private RecyclerView recyclerView;
    private static final String TAG = "PendingFragment";
    private ProgressBar progressBar;
    TextView textView;
    private UtilityMethods methods = UtilityMethods.getInstance();
    private SharedPrefrenceManager manager;
    public PendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pending, container, false);
        progressBar = view.findViewById(R.id.pending_progressbar);
        textView = view.findViewById(R.id.no_orders_textview);
        recyclerView = view.findViewById(R.id.orders_recycleView);
        manager  = new SharedPrefrenceManager(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        ToiAdminApi.getInstance().getAllNetworkRequests()
                .getAllOrders(new OrderPayload(manager.getUserName(getContext()), "2020-06-01 10:00:00"))
                .enqueue(new Callback<AllOrdersResponse>() {
                    @Override
                    public void onResponse(Call<AllOrdersResponse> call, Response<AllOrdersResponse> response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onResponse: " + response.body().getResult_Output().toString());
                        if (response.isSuccessful()) {
                               ArrayList<CustomerOrder> filteredOrders = methods.getSortedOrders( response.body().getResult_Output().getCustomerOrders(),"Pending");
                            if (filteredOrders.size() == 0) {
                                textView.setVisibility(View.VISIBLE);
                            } else {
                                textView.setVisibility(View.INVISIBLE);
                                adapter = new AllOrdersAdapter(filteredOrders, getContext(), Constants.ALL_ORDERS);
                                recyclerView.setAdapter(adapter);
                            }
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
