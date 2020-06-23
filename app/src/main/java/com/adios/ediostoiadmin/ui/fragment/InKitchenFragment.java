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
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.AllOrdersAdapter;
import com.adios.ediostoiadmin.adapters.OrderSummaryAdapter;
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
public class InKitchenFragment extends Fragment {
    private View view;
    private AllOrdersAdapter adapter;
    private RecyclerView recyclerView;
    private TextView textView;
    private static final String TAG = "PendingFragment";
    private UtilityMethods methods = UtilityMethods.getInstance();
    SharedPrefrenceManager manager = new SharedPrefrenceManager(getContext());

    public InKitchenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_in_kitchen, container, false);
        textView = view.findViewById(R.id.no_orders_textview);
        recyclerView = view.findViewById(R.id.in_kitchen_recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (methods.getSortedOrders("In-Kitchen").size() == 0) {
//            textView.setVisibility(View.VISIBLE);
//        } else {
//            textView.setVisibility(View.INVISIBLE);
//            adapter = new AllOrdersAdapter(methods.getSortedOrders("In-Kitchen"), getContext(), Constants.ALL_ORDERS);
//            recyclerView.setAdapter(adapter);
//        }

        ToiAdminApi.getInstance().getAllNetworkRequests()
                .getAllOrders(new OrderPayload(manager.getUserName(getContext()), "2020-06-01 10:00:00"))
                .enqueue(new Callback<AllOrdersResponse>() {
                    @Override
                    public void onResponse(Call<AllOrdersResponse> call, Response<AllOrdersResponse> response) {
                        //progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onResponse: " + response.body().getResult_Output().toString());
                        if (response.isSuccessful()) {
                            ArrayList<CustomerOrder> filteredOrders = methods.getSortedOrders( response.body().getResult_Output().getCustomerOrders(),"In-Kitchen");
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
                        // progressBar.setVisibility(View.INVISIBLE);
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });


    }
}
