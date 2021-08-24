package com.wapazock.doozby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.wapazock.doozby.WelcomePage.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if its the first time running this app
        checkFirstRun();

    }

    // Check First Run : Detects if its the first time to run the application
    //     If it is, then launch the Welcome activity
    private void checkFirstRun(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.first_run), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.first_run), Boolean.TRUE);
            edit.commit();

            // It is the first time the client is running this app, hence show Welcome Page
            Intent welcomeIntent = new Intent(this, WelcomeActivity.class);
            //flag to clear the backstack
            welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(welcomeIntent);

            //Close This Activity
            this.finish();
        }
    }
}