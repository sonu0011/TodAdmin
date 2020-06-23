package com.adios.ediostoiadmin.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.modal.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerAdapter extends RecyclerView.Adapter<DeliveryPartnerAdapter.ViewHolder> {
    private ArrayList<DeliveryPartner> partners;
    private Context context;

    public DeliveryPartnerAdapter(ArrayList<DeliveryPartner> partners, Context context) {
        this.partners = partners;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery_partner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeliveryPartner partner = partners.get(position);
        holder.dp_name.setText(partner.getUserName());
        holder.dp_status.setText(partner.getUserStatus());
    }

    @Override
    public int getItemCount() {
        return partners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dp_name, dp_status;
        private ImageButton call;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dp_name = itemView.findViewById(R.id.tv_partnerName);
            dp_status = itemView.findViewById(R.id.tv_partnerStatus);
            call = itemView.findViewById(R.id.call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + partners.get(getAdapterPosition()).getUserMobile()));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},1);
                        return;
                    }
                    context.startActivity(intent);
                }
            });
        }
    }
}
