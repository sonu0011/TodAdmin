package com.adios.ediostoiadmin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.AllOrdersAdapter;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.payloads.SearchOrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;
import com.adios.ediostoiadmin.ui.activity.SearchResultActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchordersFragment extends Fragment {
    private LinearLayout filterlayout;
    private View view;

    private Button search;
    AllOrdersAdapter adapter;
    private EditText c_name, c_mobile, c_email, c_orderno;
    private static final String TAG = "SearchordersFragment";
    SharedPrefrenceManager manager = new SharedPrefrenceManager(getContext());


    public SearchordersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_searchorders, container, false);
        filterlayout = view.findViewById(R.id.filter_layout);
        search = view.findViewById(R.id.search_order);
        c_name = view.findViewById(R.id.customer_name);
        c_mobile = view.findViewById(R.id.mobile);
        c_email = view.findViewById(R.id.email);
        c_orderno = view.findViewById(R.id.order_no);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchOrderPayload payload = new SearchOrderPayload(manager.getUserName(getContext()),
                        c_name.getText().toString(),
                        c_mobile.getText().toString(),
                        c_email.getText().toString(),
                        c_orderno.getText().toString());
                Intent intent = new Intent(getContext(), SearchResultActivity.class);
                intent.putExtra("searchPayload", payload);
                startActivity(intent);
            }
        });

    }
}
