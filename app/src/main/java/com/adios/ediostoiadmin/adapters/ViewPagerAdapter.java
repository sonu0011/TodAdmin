package com.adios.ediostoiadmin.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.adios.ediostoiadmin.ui.fragment.CompletedFragment;
import com.adios.ediostoiadmin.ui.fragment.InKitchenFragment;
import com.adios.ediostoiadmin.ui.fragment.PendingFragment;
import com.adios.ediostoiadmin.ui.fragment.PickUpFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private Fragment[] childFragments;
    private int tabPosition = 0;


    public ViewPagerAdapter(@NonNull FragmentManager fm ,int behaviour) {
        super(fm ,behaviour);
        childFragments = new Fragment[]{
                new PendingFragment(),
                new InKitchenFragment(),
                new PickUpFragment(),
                new CompletedFragment()
        };
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: "+position );

       return childFragments[position];

    }

    @Override
    public int getCount() {
        return childFragments.length;
    }
}
