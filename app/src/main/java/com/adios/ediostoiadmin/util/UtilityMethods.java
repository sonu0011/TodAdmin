package com.adios.ediostoiadmin.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.modal.CustomerOrder;
import com.adios.ediostoiadmin.ui.activity.LoginActivity;

import java.util.ArrayList;

public class UtilityMethods {
    private static UtilityMethods utilityMethods;
    public ArrayList<CustomerOrder> allOrders = new ArrayList<>();
    private static final String TAG = "UtilityMethods";

    public static synchronized UtilityMethods getInstance() {
        if (utilityMethods == null) {
            utilityMethods = new UtilityMethods();
            return utilityMethods;
        }
        return utilityMethods;
    }

    private UtilityMethods() {

    }

    public String showStatusDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.status_layout, null);
        final RadioGroup radioGroup = dialogLayout.findViewById(R.id.status_radioGroup);
        final String[] filteredString = new String[1];
        filteredString[0] ="";

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
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
        return filteredString[0];
    }

    public ArrayList<CustomerOrder> getSortedOrders(ArrayList<CustomerOrder> orders ,String status) {
        ArrayList<CustomerOrder> newOrders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderStatus().contains(status)) {
                newOrders.add(orders.get(i));
            }
        }
        Log.d(TAG, "getSortedOrders: " + status + newOrders.size());
        return newOrders;
    }


}
