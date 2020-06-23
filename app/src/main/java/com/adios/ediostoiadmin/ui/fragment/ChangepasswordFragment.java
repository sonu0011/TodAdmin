package com.adios.ediostoiadmin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.data.modal.ChangePasswordResponse;
import com.adios.ediostoiadmin.data.modal.payloads.ChangePasswordPayload;
import com.adios.ediostoiadmin.data.network.ToiAdminApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangepasswordFragment extends Fragment implements View.OnClickListener {
    private View view;
    private EditText old_pwd, new_pwd, con_pwd;
    private Button btn_change_pwd;
    private static final String TAG = "ChangepasswordFragment";
    ProgressBar progressBar;
    SharedPrefrenceManager manager = new SharedPrefrenceManager(getContext());

    public ChangepasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_changepassword, container, false);
        old_pwd = view.findViewById(R.id.old_password);
        new_pwd = view.findViewById(R.id.new_password);
        con_pwd = view.findViewById(R.id.new_password_confirm);
        btn_change_pwd = view.findViewById(R.id.btn_changePassword);
        progressBar = view.findViewById(R.id.login_progressbar);
        btn_change_pwd.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (vaildatePassword()) {
            progressBar.setVisibility(View.VISIBLE);
            ToiAdminApi.getInstance().getAllNetworkRequests()
                    .changePassword(new ChangePasswordPayload(manager.getUserName(getContext()), old_pwd.getText().toString(), new_pwd.getText().toString().trim()))
                    .enqueue(new Callback<ChangePasswordResponse>() {
                        @Override
                        public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.d(TAG, "onResponse: " + response.body().toString());
                            if (response.isSuccessful()) {
                                if (response.body().getResult_Status().startsWith("F")){
                                    Toast.makeText(getContext(), "Current Password does not match", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Toast.makeText(getContext(), "" + response.body().getResult_Message(), Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d(TAG, "onResponse: " + response.errorBody().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                            progressBar.setVisibility(View.INVISIBLE);

                            Log.d(TAG, "onFailure: " + t.getMessage());
                        }
                    });
        }
    }

    private boolean vaildatePassword() {
        if (old_pwd.getText().toString().trim().length() == 0){
            Toast.makeText(getContext(), "Old password can't be blank.", Toast.LENGTH_SHORT).show();
            return false;
        }
       else if (new_pwd.getText().toString().trim().length() < 5) {
            Toast.makeText(getContext(), "Password length should be at least 5 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
       else  if (con_pwd.getText().toString().trim().length() < 5){
            Toast.makeText(getContext(), "Confirm Password length should be at least 5 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
       else if (!con_pwd.getText().toString().equals(new_pwd.getText().toString())) {
            Toast.makeText(getContext(), "Please confirm your new password.", Toast.LENGTH_SHORT).show();
            return false;
        }
       return true;
    }
}
