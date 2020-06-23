package com.adios.ediostoiadmin.ui.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.AllOrdersAdapter;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.payloads.OrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;
import com.adios.ediostoiadmin.util.Constants;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderhistoryFragment extends Fragment {
    TextView dateText;
    ImageView imageView;
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private static final String TAG = "OrderhistoryFragment";
    AllOrdersAdapter adapter;
    SharedPrefrenceManager manager = new SharedPrefrenceManager(getContext());


    public OrderhistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_orderhistory, container, false);
        dateText = view.findViewById(R.id.oh_date);
        progressBar = view.findViewById(R.id.oh_progressbar);
        imageView = view.findViewById(R.id.oh_date_picker);
        recyclerView = view.findViewById(R.id.ohRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentDate = Calendar.getInstance();
                int cYear = currentDate.get(Calendar.YEAR);
                int cMonth = currentDate.get(Calendar.MONTH);
                int cDay = currentDate.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String s_date = "";
                                s_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                Log.d(TAG, "onDateSet: " + s_date);
                                dateText.setText(s_date);
                                progressBar.setVisibility(View.VISIBLE);
                                ToiAdminApi.getInstance()
                                        .getAllNetworkRequests()
                                        .getAllOrders(new OrderPayload(manager.getUserName(getContext()), s_date + " 10:00:00"))
                                        .enqueue(new Callback<AllOrdersResponse>() {
                                            @Override
                                            public void onResponse(Call<AllOrdersResponse> call, Response<AllOrdersResponse> response) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Log.d(TAG, "onResponse: " + response.body().toString());
                                                if (response.body().getResult_Output().getCustomerOrders().size() == 0) {
                                                    Toast.makeText(getContext(), "No Result Found", Toast.LENGTH_SHORT).show();
                                                    if (adapter != null) {
                                                        adapter.clearArray();
                                                    }
                                                    return;
                                                } else {
                                                    adapter = new AllOrdersAdapter(response.body().getResult_Output().getCustomerOrders(), getContext(), Constants.ORDER_SUMMARY);

                                                    recyclerView.setAdapter(adapter);
                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<AllOrdersResponse> call, Throwable t) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                Log.d(TAG, "onFailure: " + t.getMessage());
                                            }
                                        });
                            }

                        }, cYear, cMonth, cDay);


                datePickerDialog.getDatePicker().setMaxDate(currentDate.getTimeInMillis());
                datePickerDialog.show();
            }
        });

    }
}
