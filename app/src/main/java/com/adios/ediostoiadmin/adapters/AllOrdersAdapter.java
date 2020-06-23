package com.adios.ediostoiadmin.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.data.modal.Item;
import com.adios.ediostoiadmin.data.modal.payloads.UpdateOrderPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;
import com.adios.ediostoiadmin.data.network.responses.UpdateOrderResponse;
import com.adios.ediostoiadmin.ui.activity.OrderInformation;
import com.adios.ediostoiadmin.util.Constants;
import com.adios.ediostoiadmin.util.UtilityMethods;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.ViewHolder> {
    private ArrayList<CustomerOrder> orders;
    private static final String TAG = "AllOrdersAdapter";
    private Context context;
    private int show;
    SharedPrefrenceManager manager;


    public AllOrdersAdapter(ArrayList<CustomerOrder> orders, Context context, int flag) {
        this.orders = orders;
        this.context = context;
        this.show = flag;
        manager = new SharedPrefrenceManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_orders_layout, parent, false));
    }

    public void clearArray() {
        if (orders != null) {
            this.orders.clear();
            notifyDataSetChanged();
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerOrder ordersResponse = orders.get(position);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(new ItemAdapter(ordersResponse.getCustomerOrderItems()));
        holder.orderNumber.setText(ordersResponse.getOrderNumber());
        holder.order_type.setText(ordersResponse.getOrderType());
        holder.order_date.setText(ordersResponse.getOrderDateTime());
        holder.order_status.setText(ordersResponse.getOrderStatus());
        holder.order_type_status.setText(ordersResponse.getOrderStatus());
        holder.order_total_amount.setText("Total Amount : $" + ordersResponse.getTotalOrderAmount());
        holder.order_total_tax.setText("Total Tax : $" + ordersResponse.getTotalTaxAmount());
        holder.order_user_name.setText(ordersResponse.getCustomer().getUserName());
        holder.order_user_mobileno.setText(ordersResponse.getCustomer().getMobileNo());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderNumber, order_status, order_type,
                order_type_status, order_date, order_user_name, order_user_mobileno,
                order_total_tax, order_total_amount;
        private ImageView orderinfo;
        private Button order_update_status, order_kitchen_ticket, order_print_check;
        private RecyclerView recyclerView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_update_status = itemView.findViewById(R.id.order_update_status);
            order_kitchen_ticket = itemView.findViewById(R.id.order_kitchen_ticket);
            order_print_check = itemView.findViewById(R.id.order_print_check);
            recyclerView = itemView.findViewById(R.id.allorders_recycleview);
            orderNumber = itemView.findViewById(R.id.order_number);
            order_status = itemView.findViewById(R.id.order_status);
            order_type = itemView.findViewById(R.id.order_type);
            order_type_status = itemView.findViewById(R.id.order_type_status);
            order_date = itemView.findViewById(R.id.order_date);
            order_user_name = itemView.findViewById(R.id.order_placed_username);
            order_user_mobileno = itemView.findViewById(R.id.order_placed_user_mobileno);
            order_total_tax = itemView.findViewById(R.id.order_total_tax);
            order_total_amount = itemView.findViewById(R.id.order_total_price);
            orderinfo = itemView.findViewById(R.id.order_info);
            if (show == Constants.ORDER_SUMMARY) {
                order_update_status.setVisibility(View.GONE);
                order_kitchen_ticket.setVisibility(View.GONE);
                order_print_check.setText("Print Check");

            }

            orderinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderInformation.class);
                    intent.putExtra("order", orders.get(getAdapterPosition()));
                    context.startActivity(intent);
                    Log.d(TAG, "onClick: " + orders.get(getAdapterPosition()));
                }
            });
            order_update_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String status = orders.get(getAdapterPosition()).getOrderStatus();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View dialogLayout = inflater.inflate(R.layout.status_layout, null);
                    final RadioGroup radioGroup = dialogLayout.findViewById(R.id.status_radioGroup);

                    final String[] filteredString = new String[1];
                    filteredString[0] = "";
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            filteredString[0] = "";
                            RadioButton rb = group.findViewById(checkedId);
                            filteredString[0] = rb.getText().toString();
                        }
                    });
                    builder.setView(dialogLayout)
                            .setTitle("Update Status")
                            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Log.d(TAG, "onClick: " + filteredString[0]);
                                    if (filteredString[0].length() > 0) {
                                        CustomerOrder order = orders.get(getAdapterPosition());
                                        order.setOrderStatus(filteredString[0]);
                                        ToiAdminApi
                                                .getInstance().getAllNetworkRequests()
                                                .updateOrder(new UpdateOrderPayload(
                                                        manager.getUserName(context),
                                                        order)
                                                )
                                                .enqueue(new Callback<UpdateOrderResponse>() {
                                                    @Override
                                                    public void onResponse(Call<UpdateOrderResponse> call, Response<UpdateOrderResponse> response) {
                                                        Log.d(TAG, "onResponse: " + response.body());
                                                        if (response.isSuccessful()) {
                                                            if (response.body().getResult_Status().startsWith("F")) {
                                                                Toast.makeText(context, "Error in updating status", Toast.LENGTH_SHORT).show();
                                                                return;
                                                            }
                                                            orders.remove(getAdapterPosition());
                                                            notifyItemRemoved(getAdapterPosition());
                                                            notifyItemRangeChanged(getAdapterPosition(), orders.size());
                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<UpdateOrderResponse> call, Throwable t) {
                                                        Log.d(TAG, "onFailure: " + t.getMessage());

                                                    }
                                                });

                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });


        }
    }
}
