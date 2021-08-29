package com.wapazock.doozby.WelcomePage;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.wapazock.doozby.CreateAccountPage.CreateUsernameActivity;
import com.wapazock.doozby.HomeActivity.HomeActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.SignInPage.SignInActivity;
import com.wapazock.doozby.Utils.LayoutHelper;

import spencerstudios.com.bungeelib.Bungee;

public class WelcomeActivity extends AppCompatActivity {

    // View
    private SurfaceView welcomeVideoSurface;

    // Variables
    private WelcomeActivityViewModel welcomeActivityViewModel;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Make fullscreen with a translucent status Bar
        setFullScreen();

        // Assign View Elements
        welcomeVideoSurface = findViewById(R.id.welcomePageBackgroundSurfaceView);

        // Set the activity View Controller
        welcomeActivityViewModel = new ViewModelProvider(this).get(WelcomeActivityViewModel.class);

        // Bind video with the view model
        bindVideo();

    }



    // Make FullScreen : Sets the activity to become fullscreen with a translucent
    //                   status bar
    private void setFullScreen(){
        LayoutHelper.removeLimits(WelcomeActivity.this);
        LayoutHelper.setTranslucent(WelcomeActivity.this);
    }

    // Bind Video : Binds the welcome video to the welcome
    //          SurfaceView, and plays the video in an infinite loop
    public void bindVideo(){
        //get welcomeVideoSurface holder
        SurfaceHolder welcomeVideoSurfaceHolder = welcomeVideoSurface.getHolder();

        //add call back to the welcomeVideoSurfaceHolder
        welcomeVideoSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                //bind surface with view model
                welcomeActivityViewModel.getMediaPlayer().getValue().setDisplay(welcomeVideoSurfaceHolder);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    // Skip Button Clicked : Triggered by the click of the skip button
    //          On click - starts the MainActivity while clearing the backstack
    public void skipTextViewClicked(View view) {
        Intent mainActivityIntent = new Intent(this, HomeActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        WelcomeActivity.this.startActivity(mainActivityIntent);
        Bungee.slideLeft(WelcomeActivity.this);
        WelcomeActivity.this.finish();
    }

    // Sign In Button Clicked : Triggered by the click of the sign in button
    //          On click - starts the SignInActivity while clearing the backstack
    public void signInButtonClicked(View view) {
        Intent mainActivityIntent = new Intent(this, SignInActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        WelcomeActivity.this.startActivity(mainActivityIntent);
        Bungee.slideLeft(WelcomeActivity.this);
        WelcomeActivity.this.finish();
    }

    // Create Account Button Clicked : Triggered by the click of the create account button
    //          On click - starts the CreateAccountActivity while clearing the backstack
    public void createAccountButtonClicked(View view) {
        Intent mainActivityIntent = new Intent(this, CreateUsernameActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        WelcomeActivity.this.startActivity(mainActivityIntent);
        Bungee.slideLeft(WelcomeActivity.this);
        WelcomeActivity.this.finish();
    }


    // On Destroy : When activity is destroyed release the media player
    //      otherwise it will keep playing in the background
    @Override
    protected void onDestroy() {
        super.onDestroy();
        welcomeActivityViewModel.activityStopped();
    }

    // On Destroy : When activity is paused release the media player
    //      otherwise it will keep playing in the background
    @Override
    protected void onPause() {
        super.onPause();
        welcomeActivityViewModel.activityPaused();
    }

    // On Resume : Resume playing the video
    @Override
    protected void onResume() {
        super.onResume();
        welcomeActivityViewModel.activityResumed();
    }
}
