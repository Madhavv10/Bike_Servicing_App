package com.example.bike_servicing;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tab_Adapter extends FragmentPagerAdapter {

    int totalTabs =3;

    public tab_Adapter( FragmentManager fm){
        //super(fm);
        super(fm,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                mybike_tab bike_frag = new mybike_tab();
                return bike_frag;
            case 1:
                myaddress_tab address_frag = new myaddress_tab();
                return address_frag;
            case 2:
                mycredits_tab credits_frag = new mycredits_tab();
                return credits_frag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
            return 3;
    }
}
