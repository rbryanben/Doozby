package com.wapazock.doozby.AccountActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.wapazock.doozby.HomeActivity.HomeActivity;
import com.wapazock.doozby.PostActivity.PostActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.TrendingActivity.TrendingActivity;

import org.jetbrains.annotations.NotNull;

public class AccountActivity extends AppCompatActivity {

    // View
    private BottomNavigationView accountActivityBottomNavigationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // View
        accountActivityBottomNavigationView = findViewById(R.id.accountActivityBottomNavigationView);

        // Bind Bottom Navigation View
        bindBottomNavigationView();
    }


    // Bind Bottom Navigation View -- Binds the bottom navigation
    private void bindBottomNavigationView() {
        // Set trending As Selected
        accountActivityBottomNavigationView.setSelectedItemId(R.id.menuAccount);

        // Add on click listener
        accountActivityBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                // Set Intent To Start As Nothing
                Intent intentToStart;

                switch (item.getItemId()){
                    case R.id.menuHome:
                        intentToStart = new Intent(AccountActivity.this, HomeActivity.class);
                        break;
                    case R.id.menuPost:
                        intentToStart = new Intent(AccountActivity.this, PostActivity.class);
                        break;
                    case R.id.menuTrending:
                        intentToStart = new Intent(AccountActivity.this, TrendingActivity.class);
                        break;
                    default:
                        intentToStart = null;
                }


                // Start Intent in intentToStart is not null
                if (intentToStart != null) {
                    AccountActivity.this.startActivity(intentToStart);
                }

                // return false
                return false;
            }
        });
    }

}
