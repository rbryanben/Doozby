package com.wapazock.doozby.TrendingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.wapazock.doozby.AccountActivity.AccountActivity;
import com.wapazock.doozby.HomeActivity.HomeActivity;
import com.wapazock.doozby.PostActivity.PostActivity;
import com.wapazock.doozby.R;

import org.jetbrains.annotations.NotNull;

public class TrendingActivity extends AppCompatActivity {

    // View
    BottomNavigationView trendingActivityBottomNavigationView;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        // View
        trendingActivityBottomNavigationView = findViewById(R.id.trendingActivityBottomNavigationView);

        // Bind bottom navigation view
        bindBottomNavigationView();
    }


    // Bind Bottom Navigation View -- Binds the bottom navigation
    private void bindBottomNavigationView() {
        // Set trending As Selected
        trendingActivityBottomNavigationView.setSelectedItemId(R.id.menuTrending);
        // Add on click listener
        trendingActivityBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                // Set Intent To Start As Nothing
                Intent intentToStart;

                switch (item.getItemId()){
                    case R.id.menuHome:
                        intentToStart = new Intent(TrendingActivity.this, HomeActivity.class);
                        break;
                    case R.id.menuPost:
                        intentToStart = new Intent(TrendingActivity.this, PostActivity.class);
                        break;
                    case R.id.menuAccount:
                        intentToStart = new Intent(TrendingActivity.this, AccountActivity.class);
                        break;
                    default:
                        intentToStart = null;
                }


                // Start Intent in intentToStart is not null
                if (intentToStart != null) {
                    TrendingActivity.this.startActivity(intentToStart);
                }

                // return false
                return false;
            }
        });
    }

}
