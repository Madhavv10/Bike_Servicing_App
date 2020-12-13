package com.example.bike_servicing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class profile_tab_fragment extends Fragment {


    private ViewPager viewPager;
    private TextView bikes,address,credits;
    private tab_Adapter pagerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_tab_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*bikes = getView().findViewById(R.id.my_bike_tv);
        address = getView().findViewById(R.id.my_address_tv);
        credits = getView().findViewById(R.id.my_credits_tv);
        viewPager= getView().findViewById(R.id.tabs_container);


        bikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });


        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        pagerViewAdapter = new tab_Adapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerViewAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                onChangeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void onChangeTab(int position) {

        if(position==0)
        {
            bikes.setTextSize(25);
        }
        if(position==1)
        {

            address.setTextSize(25);

        }
        if(position==2)
        {
           credits.setTextSize(25);
        }

         */



    }

}
