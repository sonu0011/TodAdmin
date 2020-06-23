package com.adios.ediostoiadmin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.modal.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Item> items;

    public ItemAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.qty.setText(String.valueOf(Math.round(item.getItemQuantity())) +" X ");
        holder.name.setText(item.getItemName());
        holder.price.setText("$" + item.getItemPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView qty, name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qty = itemView.findViewById(R.id.io_itemQuantity);
            name = itemView.findViewById(R.id.to_itemName);
            price = itemView.findViewById(R.id.io_itemPrice);

        }
    }
}
