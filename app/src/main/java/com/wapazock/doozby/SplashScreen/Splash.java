package com.wapazock.doozby.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wapazock.doozby.HomeActivity.HomeActivity;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.WelcomePage.WelcomeActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check first run
        if (hasToken()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }

    }

    // Has Token : Checks if the user is signed in,
    //      it does this by checking if there is an active token in the shared preferences
    private Boolean hasToken(){
        //Shared preferences
        SharedPreferences preferences = getSharedPreferences("CREDENTIALS",MODE_PRIVATE);
        String TOKEN = preferences.getString("TOKEN",null);
        if (TOKEN == null){
            return false;
        }

        // Set the token in the repository
        DoozbyRepository.setTOKEN(TOKEN);

        return true;
    }
}
