package com.wapazock.doozby.HomeActivity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeActivityFragmentsAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> FRAGMENTS;
    Context CONTEXT;

    public HomeActivityFragmentsAdapter(@NonNull @NotNull FragmentManager fm, ArrayList<Fragment> FRAGMENTS, Context context) {
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
