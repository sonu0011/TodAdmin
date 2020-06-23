package com.adios.ediostoiadmin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.modal.OrderSummary;

import java.util.ArrayList;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {
    private ArrayList<OrderSummary> list;

    public OrderSummaryAdapter(ArrayList<OrderSummary> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderSummary summary = list.get(position);
        holder.amount.setText("$" + summary.getTotalAmount());
        holder.date.setText(summary.getOrderDate());
        holder.order_count.setText(String.valueOf(summary.getTotalOrders()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date, order_count, amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.os_date);
            order_count = itemView.findViewById(R.id.os_count);
            amount = itemView.findViewById(R.id.os_amount);
        }
    }
}
