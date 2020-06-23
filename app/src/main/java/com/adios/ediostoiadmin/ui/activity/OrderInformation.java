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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.adapters.ItemAdapter;
import com.adios.ediostoiadmin.data.modal.Customer;
import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.data.modal.DeliveryPartner;
import com.adios.ediostoiadmin.data.modal.Item;
import com.adios.ediostoiadmin.data.network.responses.AllOrdersResponse;

public class OrderInformation extends AppCompatActivity {
    CustomerOrder customerOrder;
    Item item;
    Customer customer;
    private TextView user_name, address, user_mobileno, number_status, order_type, order_date, dp_name, dp_status,
            dp_status_time, total_tax, total_amount, other_inst;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;


    private static final String TAG = "OrderInformation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_information);
        getSupportActionBar().setTitle("Order Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        customerOrder = getIntent().getParcelableExtra("order");
        customer = customerOrder.getCustomer();
        Log.d(TAG, "onCreate: " + customerOrder);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

    private void initViews() {
        user_name = findViewById(R.id.info_username);
        user_mobileno = findViewById(R.id.info_mobileno);
        number_status = findViewById(R.id.oi_item_name_status);
        order_type = findViewById(R.id.oi_type);
        order_date = findViewById(R.id.oi_date);
        total_tax = findViewById(R.id.oi_item_total_tax);
        total_amount = findViewById(R.id.oi_item_total_amount);
        other_inst = findViewById(R.id.oi_item_other_instruction);
        dp_status = findViewById(R.id.info_dp_status);
        dp_name = findViewById(R.id.info_dp_name);
        dp_status_time = findViewById(R.id.info_dp_date);
        imageView = findViewById(R.id.info_addDp);
        linearLayout = findViewById(R.id.info_ll_dp);
        recyclerView = findViewById(R.id.info_items_recycleview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        user_name.setText(customer.getUserName());
        user_mobileno.setText(customer.getMobileNo());
        order_type.setText(customerOrder.getOrderType());
        order_date.setText(customerOrder.getOrderDateTime());
        if (!customerOrder.getOrderInstructions().isEmpty()) {
            other_inst.setText(customerOrder.getOrderInstructions());

        }
        number_status.setText(customerOrder.getOrderNumber() + " (" + customerOrder.getOrderStatus() + ")");
        if (customerOrder.getOrderType().contains("Delivery")) {
            findViewById(R.id.line2).setVisibility(View.VISIBLE);
            findViewById(R.id.line11).setVisibility(View.VISIBLE);
            dp_status_time.setText(customerOrder.getOrderDateTime());
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ItemAdapter adapter = new ItemAdapter(customerOrder.getCustomerOrderItems());
            float t_tax = 0, t_amount = 0;
            for (int i = 0; i < customerOrder.getCustomerOrderItems().size(); i++) {
                t_tax += customerOrder.getCustomerOrderItems().get(i).getItemTaxAmount();
                t_amount += customerOrder.getCustomerOrderItems().get(i).getItemTotalAmount();

            }
            recyclerView.setAdapter(adapter);
            total_tax.setText("Total Tax : $" + t_tax);
            total_amount.setText("Total Amount : $" + t_amount);


        } else {
            findViewById(R.id.line2).setVisibility(View.INVISIBLE);
            findViewById(R.id.line11).setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.GONE);

        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderInformation.this, DeliveryPartnerActivity.class));
            }
        });
    }


}
