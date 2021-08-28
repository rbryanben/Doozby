package com.wapazock.doozby.MainActivityFragments.HomeFragmentFragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wapazock.doozby.MainActivityFragments.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragmentsAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> FRAGMENTS;
    Context CONTEXT;

    public HomeFragmentsAdapter(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> FRAGMENTS, HomeFragment CONTEXT) {
        super(fm);
        this.FRAGMENTS = FRAGMENTS;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return FRAGMENTS.get(position);
    }

    @Override
    public int getCount() {
        return FRAGMENTS.size();
    }
}
