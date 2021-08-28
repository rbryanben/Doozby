package com.wapazock.doozby.MainActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wapazock.doozby.MainActivityFragments.HomeFragmentFragments.AllContentFragment;
import com.wapazock.doozby.MainActivityFragments.HomeFragmentFragments.HomeFragmentsAdapter;
import com.wapazock.doozby.MainActivityFragments.HomeFragmentFragments.RecommendationsFragments;
import com.wapazock.doozby.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    // View
    ViewPager homeFragmentViewPager ;
    TabLayout homeFragmentTabLayout;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_activity_home_fragment,container,false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // View
        homeFragmentViewPager = view.findViewById(R.id.homeFragmentViewPager);
        homeFragmentTabLayout = view.findViewById(R.id.homeFragmentTabLayout);

        // Set Adapter
        setFragmentsAdapter();
    }

    // Sets Fragments Adapter : Sets adapter for the homeFragmentViewPager
    private void setFragmentsAdapter() {
        // Fragments
        Fragment home = new AllContentFragment();
        Fragment recommendations = new RecommendationsFragments();

        // Arraylist
        ArrayList<Fragment> FRAGMENTS = new ArrayList<>();
        FRAGMENTS.add(home);
        FRAGMENTS.add(recommendations);

        //Create an adapter instance
        HomeFragmentsAdapter adapter = new HomeFragmentsAdapter(getFragmentManager(),FRAGMENTS,this);

        // Set adapter
        homeFragmentViewPager.setAdapter(adapter);

        // Bind with tab-layout
        homeFragmentTabLayout.setupWithViewPager(homeFragmentViewPager);

        // Tab Layout Set Tabs
        homeFragmentTabLayout.getTabAt(0).setText("For You");
        homeFragmentTabLayout.getTabAt(1).setText("All");

    }


}
