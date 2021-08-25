package com.wapazock.doozby.WelcomePage;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.LayoutHelper;

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
        welcomeActivityViewModel.activityStopped();
    }
}
