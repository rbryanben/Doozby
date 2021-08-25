package com.wapazock.doozby.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wapazock.doozby.MainActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.WelcomePage.WelcomeActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check first run
        if (isFirstRun()) {
            startActivity(new Intent(this, WelcomeActivity.class));
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    // Check First Run : Detects if its the first time to run the application
    //     If it is, then launch the Welcome activity
    private Boolean isFirstRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.first_run), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.first_run), Boolean.TRUE);
            edit.commit();
            return true ;
        }

        return false;
    }
}
