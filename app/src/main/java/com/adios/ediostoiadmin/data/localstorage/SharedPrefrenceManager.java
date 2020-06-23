package com.adios.ediostoiadmin.data.localstorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.adios.ediostoiadmin.data.modal.AdminSettings;
import com.adios.ediostoiadmin.data.modal.AllSettings;
import com.google.gson.Gson;

public class SharedPrefrenceManager {
    private Context context;

    public SharedPrefrenceManager(Context context) {
        this.context = context;
    }

    public void storeSettings(AllSettings settings) {
        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(settings);
        editor.putString("settings", json);
        editor.commit();
    }

    public void storeUsernameAndPassword(String userName, String password) {
        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("userName", userName);
        editor.putString("password", password);
        editor.commit();

    }

    public String getUserName(Context mContext) {
        SharedPreferences pref = mContext.getSharedPreferences("MyPref", 0); // 0 - for private mode
        return pref.getString("userName", "");
    }


    public AllSettings getSettings() {
        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        Gson gson = new Gson();
        String json = pref.getString("settings", "");
        AllSettings obj = gson.fromJson(json, AllSettings.class);
        return obj;
    }


}
