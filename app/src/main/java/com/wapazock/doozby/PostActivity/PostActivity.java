package com.wapazock.doozby.PostActivity;

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
import com.wapazock.doozby.R;
import com.wapazock.doozby.TrendingActivity.TrendingActivity;

import org.jetbrains.annotations.NotNull;

public class PostActivity extends AppCompatActivity {

    // View
    private BottomNavigationView postActivityBottomNavigationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // View
        postActivityBottomNavigationView = findViewById(R.id.postActivityBottomNavigationView);

        // Bind Bottom Navigation View
        bindBottomNavigationView();

    }

    // Bind Bottom Navigation View -- Binds the bottom navigation
    private void bindBottomNavigationView() {
        // Set trending As Selected
        postActivityBottomNavigationView.setSelectedItemId(R.id.menuPost);
        // Add on click listener
        postActivityBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                // Set Intent To Start As Nothing
                Intent intentToStart;

                switch (item.getItemId()){
                    case R.id.menuHome:
                        intentToStart = new Intent(PostActivity.this, HomeActivity.class);
                        break;
                    case R.id.menuTrending:
                        intentToStart = new Intent(PostActivity.this, TrendingActivity.class);
                        break;
                    case R.id.menuAccount:
                        intentToStart = new Intent(PostActivity.this, AccountActivity.class);
                        break;
                    default:
                        intentToStart = null;
                }


                // Start Intent in intentToStart is not null
                if (intentToStart != null) {
                    PostActivity.this.startActivity(intentToStart);
                }

                // return false
                return false;
            }
        });
    }

}
