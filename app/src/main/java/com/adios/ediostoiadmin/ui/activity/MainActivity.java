package com.adios.ediostoiadmin.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adios.ediostoiadmin.R;
import com.adios.ediostoiadmin.data.localstorage.SharedPrefrenceManager;
import com.adios.ediostoiadmin.ui.fragment.ChangepasswordFragment;
import com.adios.ediostoiadmin.ui.fragment.DashboardFragment;
import com.adios.ediostoiadmin.ui.fragment.OrderhistoryFragment;
import com.adios.ediostoiadmin.ui.fragment.OrdersummaryFragment;
import com.adios.ediostoiadmin.ui.fragment.SearchordersFragment;
import com.adios.ediostoiadmin.ui.fragment.SettingsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Toolbar toolbar;
    private SharedPrefrenceManager manager;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);
        manager = new SharedPrefrenceManager(this);
        Log.d(TAG, "onCreate: username is" + manager.getUserName(this));
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new DashboardFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_dashboard);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            shoFilterDialog();
        } else if (item.getItemId() == R.id.action_refresh) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DashboardFragment()).commit();
            //refresh the layout
        }
        return super.onOptionsItemSelected(item);
    }

    private void shoFilterDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.order_filter_type);
        final String[] filteredString = new String[1];
        RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                filteredString[0] = "";
                filteredString[0] = rb.getText().toString();
            }
        });


        TextView cancel = dialog.findViewById(R.id.filter_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView ok = dialog.findViewById(R.id.filter_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filteredString[0] != null) {
                    Toast.makeText(MainActivity.this, "" + filteredString[0], Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                toolbar.setTitle("Dashboard");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DashboardFragment()).commit();
                break;
            case R.id.nav_order_history:
                toolbar.setTitle("Order History");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrderhistoryFragment()).commit();
                break;
            case R.id.nav_order_summary:
                toolbar.setTitle("Order Summary");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrdersummaryFragment()).commit();
                break;
            case R.id.nav_search_orders:
                toolbar.setTitle("Search Orders");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchordersFragment()).commit();
                break;
            case R.id.nav_change_password:
                toolbar.setTitle("Change Password");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChangepasswordFragment()).commit();
                break;
            case R.id.nav_settings:
                toolbar.setTitle("Settings");

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
