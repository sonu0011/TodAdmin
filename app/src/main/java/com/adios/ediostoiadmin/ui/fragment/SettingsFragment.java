package com.adios.ediostoiadmin.ui.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.AdminSettings;
import com.adios.ediostoiadmin.data.modal.AllSettings;
import com.adios.ediostoiadmin.data.modal.GenericResponse;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private View view;
    private EditText ready_time, refresh_time, deliveryChargesAmount, deliveryRadius, deliveryTime;
    private SwitchCompat euAppShutdown, autoPrint;
    private AppCompatSpinner deliveryChargesType, orderType, print_size_spinner;
    boolean shutdown = false;
    private String chargesTypeVal = "Fixed", order_typeVal = "Both", printSizeVal = "TSP 3 Inch";
    private String[] deliveryCharges;
    private String[] printerSizes;
    private String[] orderTypes;
    private Button btn_settings;
    private ProgressBar progressBar;
    private static final String TAG = "SettingsFragment";
    private SharedPrefrenceManager sharedPrefrenceManager;
    private int printCount = 0, typeCount = 0, chargesCount = 0;
    private boolean autoPrintBool = false;
    AllSettings allSettings;
    AdminSettings settings;
    SharedPrefrenceManager manager = new SharedPrefrenceManager(getContext());

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        sharedPrefrenceManager = new SharedPrefrenceManager(getContext());
        initViews();
        if (sharedPrefrenceManager.getSettings() != null) {
            allSettings = sharedPrefrenceManager.getSettings();
            settings = allSettings.getAdminSettings();
            Log.d(TAG, "onCreateView: " + allSettings.toString());

            ready_time.setText(settings.getReadyTimeInMin());
            refresh_time.setText(allSettings.getRefresh_time());
            autoPrint.setChecked(allSettings.isAutoPrint());
            deliveryRadius.setText(settings.getDeliveryRadius());
            deliveryTime.setText(settings.getDeliveryTime());
            deliveryChargesAmount.setText(String.valueOf(settings.getDeliveryChargesAmount()));
            euAppShutdown.setChecked(getBooleanFromString(settings.getEuAppShutdown()));

        }


        return view;
    }

    private void initViews() {
        ready_time = view.findViewById(R.id.et_ready_time);
        refresh_time = view.findViewById(R.id.et_refresh_time);
        deliveryTime = view.findViewById(R.id.et_delivery_pick_time);
        deliveryChargesAmount = view.findViewById(R.id.et_deliveryCharges);
        deliveryRadius = view.findViewById(R.id.et_deliveryRadius);
        btn_settings = view.findViewById(R.id.btn_update_settings);
        progressBar = view.findViewById(R.id.setting_progressbar);

        euAppShutdown = view.findViewById(R.id.switch_euShutdown);
        autoPrint = view.findViewById(R.id.switch_auto_print);
        deliveryChargesType = view.findViewById(R.id.spin_deliveryCharges);
        orderType = view.findViewById(R.id.spin_orderType);
        print_size_spinner = view.findViewById(R.id.print_size_spinner);

        deliveryCharges = view.getResources().getStringArray(R.array.delivery_charges);
        orderTypes = view.getResources().getStringArray(R.array.orderTypes);
        printerSizes = view.getResources().getStringArray(R.array.print_size_array);

    }


    @Override
    public void onStart() {
        super.onStart();

        euAppShutdown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shutdown = isChecked;
            }
        });

        autoPrint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                autoPrintBool = isChecked;
            }
        });


        deliveryChargesType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (chargesCount == 0) {
                    if (allSettings != null) {
                        if (sharedPrefrenceManager.getSettings() != null) {
                            ((TextView) view).setText(sharedPrefrenceManager.getSettings().getAdminSettings().getDeliveryChargesType());
                        }
                    }
                } else {
                    chargesCount++;
                }

                chargesTypeVal = deliveryCharges[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        print_size_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (printCount == 0) {
                    if (allSettings != null) {
                        if (sharedPrefrenceManager.getSettings() != null) {
                            ((TextView) view).setText(sharedPrefrenceManager.getSettings().getPrintSize());
                        }
                    }
                } else {
                    printCount++;
                }
                printSizeVal = printerSizes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        orderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (typeCount == 0) {
                    if (allSettings != null) {
                        if (sharedPrefrenceManager.getSettings() != null) {
                            ((TextView) view).setText(sharedPrefrenceManager.getSettings().getAdminSettings().getOrderType());
                        }
                    }
                } else {
                    typeCount++;
                }
                order_typeVal = orderTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean getBooleanFromString(String euAppShutdown) {
        return euAppShutdown.contains("Yes");
    }

    @Override
    public void onResume() {
        super.onResume();
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final AdminSettings settings = new AdminSettings(manager.getUserName(getContext()),
                        ready_time.getText().toString(),
                        getStringfromBoolean(shutdown),
                        chargesTypeVal,
                        order_typeVal,
                        deliveryChargesAmount.getText().toString(),
                        deliveryRadius.getText().toString(),
                        deliveryTime.getText().toString());
                final AllSettings allSettings = new AllSettings(settings, refresh_time.getText().toString(), autoPrintBool, printSizeVal);

                ToiAdminApi.getInstance()
                        .getAllNetworkRequests()
                        .updateSettings(settings)
                        .enqueue(new Callback<GenericResponse>() {

                            @Override
                            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Log.d(TAG, "onResponse: " + response.body().toString());
                                if (response.isSuccessful()) {
                                    sharedPrefrenceManager.storeSettings(allSettings);
                                    Toast.makeText(getContext(), "" + response.body().getResult_Message(), Toast.LENGTH_SHORT).show();

                                } else {

                                    Log.d(TAG, "onResponse: Error in getting Response");
                                }
                            }

                            @Override
                            public void onFailure(Call<GenericResponse> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t.getMessage());
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });
    }

    private String getStringfromBoolean(boolean shutdown) {
        if (shutdown) return "Yes";
        else return "No";
    }
}
