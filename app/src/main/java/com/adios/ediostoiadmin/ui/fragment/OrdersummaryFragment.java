package com.adios.ediostoiadmin.ui.fragment;

import android.app.DatePickerDialog;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.OrderSummaryAdapter;
import com.adios.ediostoiadmin.data.modal.OrderSummary;
import com.adios.ediostoiadmin.data.modal.payloads.OderSummaryPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.OrderSummaryResponse;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersummaryFragment extends Fragment implements View.OnClickListener {
    private View view;
    private RecyclerView recyclerView;
    TextView total_amount, start_date, end_date;
    private ImageView imageView;
    private String s_date, e_date;
    private ProgressBar progressBar;
    private static final String TAG = "OrdersummaryFragment";
    private OrderSummaryAdapter adapter;
    final Calendar currentDate = Calendar.getInstance();
    int cYear = currentDate.get(Calendar.YEAR);
    int cMonth = currentDate.get(Calendar.MONTH);
    int cDay = currentDate.get(Calendar.DAY_OF_MONTH);

    public OrdersummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ordersummary, container, false);
        recyclerView = view.findViewById(R.id.os_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        total_amount = view.findViewById(R.id.total_summary_amount);
        start_date = view.findViewById(R.id.start_date);
        end_date = view.findViewById(R.id.end_date);
        end_date.setText(cYear + "-" + cMonth + "-" + cDay);
        start_date.setText(cYear + "-" + cMonth + "-" + 01);
        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        imageView = view.findViewById(R.id.os_search);
        imageView.setOnClickListener(this);
        progressBar = view.findViewById(R.id.summary_progressbar);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        s_date = start_date.getText().toString() + " " + "10:00:00";
        e_date = end_date.getText().toString() + " " + "10:00:00";
        getData(s_date, e_date);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_date) {
            showDatePicker(true);
        }
        if (v.getId() == R.id.end_date) {
            showDatePicker(false);
        }
        if (v.getId() == R.id.os_search) {
            s_date = s_date + " " + "10:00:00";
            //e_date = e_date + " " + String.valueOf(cHour - 12) + ":" + cMinute + ":" + cSecond;
            e_date = e_date + " " + "10:00:00";
            getData(s_date, e_date);
        }
    }

    private void getData(String startDate, String endDate) {
        progressBar.setVisibility(View.VISIBLE);

        Log.d(TAG, "getData: start date is" + s_date);
        Log.d(TAG, "getData: end date is" + e_date);

        ToiAdminApi.getInstance().getAllNetworkRequests()
                .getOrdersSummary(new OderSummaryPayload("toidadmin", s_date, e_date))
                .enqueue(new Callback<OrderSummaryResponse>() {
                    @Override
                    public void onResponse(Call<OrderSummaryResponse> call, Response<OrderSummaryResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onResponse: " + response.body().toString());
                        Log.d(TAG, "onResponse: size of array summry is " + response.body().getResult_Output().size());

                        if (response.isSuccessful()) {
                            ArrayList<OrderSummary> summaries = response.body().getResult_Output();
                            if (summaries.size() == 0) {
                                Toast.makeText(getContext(), "No Result Found", Toast.LENGTH_LONG).show();
                                return;
                            }
                            adapter = new OrderSummaryAdapter(response.body().getResult_Output());
                            float total = 0;
                            for (int i = 0; i < summaries.size(); i++) {

                                total += summaries.get(i).getTotalAmount();


                            }

                            recyclerView.setAdapter(adapter);
                            total_amount.setText("Total Amount : $" + total);

                        }
                    }

                    @Override
                    public void onFailure(Call<OrderSummaryResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });


    }

    private void showDatePicker(final boolean flag) {
        final Calendar currentDate = Calendar.getInstance();
        int cYear = currentDate.get(Calendar.YEAR);
        int cMonth = currentDate.get(Calendar.MONTH);
        int cDay = currentDate.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (flag) {
                            s_date = "";
                            s_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            Log.d(TAG, "onDateSet: " + s_date);
                            start_date.setText(s_date);
                        }
                        if (!flag) {
                            e_date = "";
                            e_date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            Log.d(TAG, "onDateSet: " + e_date);
                            end_date.setText(e_date);
                        }

                    }
                }, cYear, cMonth, cDay);
        datePickerDialog.getDatePicker().setMaxDate(currentDate.getTimeInMillis());
        datePickerDialog.show();


    }
}

