package com.wapazock.doozby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.wapazock.doozby.MainActivityFragments.AccountFragment;
import com.wapazock.doozby.MainActivityFragments.HomeFragment;
import com.wapazock.doozby.MainActivityFragments.PostFragment;
import com.wapazock.doozby.MainActivityFragments.TrendingFragment;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    //View
    BottomNavigationView bottomNavigationView;
    Toolbar mainActivityToolbar;

    // Variables
    Fragment homeFragment, trendingFragment, postFragment, accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View
        bottomNavigationView = findViewById(R.id.mainActivityBottomNavigationView);
        //mainActivityToolbar = findViewById(R.id.mainActivityToolbar);

        // Instance Fragments
        homeFragment = new HomeFragment();
        trendingFragment = new TrendingFragment();
        postFragment = new PostFragment();
        accountFragment = new AccountFragment();

        // Bind bottom navigation view
        bindBottomNavigationView();

    }

    // Bind Bottom Navigation View -- Binds the bottom navigation
    private void bindBottomNavigationView() {
        // Set fragment as homeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,homeFragment).commit();

        // Add on click listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                //store the selected fragment
                Fragment selectedFragment = homeFragment;

                switch (item.getItemId()){
                    case R.id.menuHome:
                        selectedFragment = homeFragment;
                        break;
                    case R.id.menuTrending:
                        selectedFragment = new TrendingFragment();
                        break;
                    case R.id.menuPost:
                        selectedFragment = postFragment;
                        break;
                    case R.id.menuAccount:
                        selectedFragment = accountFragment;
                        break;
                }

                // place fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,selectedFragment).commit();

                // return true
                return true;
            }
        });
    }

}