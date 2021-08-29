package com.wapazock.doozby.HomeActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.wapazock.doozby.AccountActivity.AccountActivity;
import com.wapazock.doozby.HomeActivity.HomeActivityFragments.AllContentFragment;
import com.wapazock.doozby.HomeActivity.HomeActivityFragments.ForYouFragment;
import com.wapazock.doozby.PostActivity.PostActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.TrendingActivity.TrendingActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

public class HomeActivity extends AppCompatActivity {

    //View
    private BottomNavigationView homeActivityBottomNavigationView;
    private ViewPager homeActivityViewPager;
    private TabLayout homeActivityTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // View
        homeActivityViewPager = findViewById(R.id.homeActivityViewPager);
        homeActivityBottomNavigationView = findViewById(R.id.homeActivityBottomNavigationView);
        homeActivityTabLayout = findViewById(R.id.homeActivityTabLayout);

        // Bind view pager, setup fragments
        bindViewPager();

        // Bind bottom navigation view
        bindBottomNavigationView();
    }

    // Bind View Pager : Sets the view pager fragments
    private void bindViewPager(){
        // Home fragments array
        ArrayList<Fragment> homeFragments = new ArrayList<>();
        homeFragments.add(new AllContentFragment());
        homeFragments.add(new ForYouFragment());

        // Create adapter
        HomeActivityFragmentsAdapter homeActivityViewPagerAdapter = new HomeActivityFragmentsAdapter(getSupportFragmentManager(),homeFragments,HomeActivity.this);

        // Set View Pager Adapter and Layout Manager
        homeActivityViewPager.setAdapter(homeActivityViewPagerAdapter);

        // Setup With View Pager
        homeActivityTabLayout.setupWithViewPager(homeActivityViewPager);
        homeActivityTabLayout.getTabAt(0).setText("For You");
        homeActivityTabLayout.getTabAt(1).setText("All");
    }


    // Bind Bottom Navigation View -- Binds the bottom navigation
    private void bindBottomNavigationView() {
        // Add on click listener
        homeActivityBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                // Set Intent To Start As Nothing
                Intent intentToStart;

                switch (item.getItemId()){
                    case R.id.menuTrending:
                        intentToStart = new Intent(HomeActivity.this, TrendingActivity.class);
                        break;
                    case R.id.menuPost:
                        intentToStart = new Intent(HomeActivity.this, PostActivity.class);
                        break;
                    case R.id.menuAccount:
                        intentToStart = new Intent(HomeActivity.this,AccountActivity.class);
                        break;
                    default:
                        intentToStart = null;
                }


                // Start Intent in intentToStart is not null
                if (intentToStart != null) {
                    HomeActivity.this.startActivity(intentToStart);
                }

                // return false
                return false;
            }
        });
    }

}