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
import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.LayoutHelper;

public class WelcomeActivity extends AppCompatActivity  implements MediaPlayer.OnPreparedListener {

    // View
    private SurfaceView welcomeVideoSurface;

    // Variables
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // make the page fullscreen
        LayoutHelper.removeLimits(WelcomeActivity.this);
        LayoutHelper.setTranslucent(WelcomeActivity.this);

        //set view elements
        welcomeVideoSurface = findViewById(R.id.welcomePageBackgroundSurfaceView);

        //bind the video with the surface view
        bindVideo();
    }

    // Bind Video : Binds the welcome video to the welcome
    //          SurfaceView, and plays the video in an infinite loop
    private void bindVideo(){
        //video url
        String welcomeVideoURL = getString(R.string.welcome_video_url) ;

        //get welcomeVideoSurface holder
        SurfaceHolder welcomeVideoSurfaceHolder = welcomeVideoSurface.getHolder();

        //add call back to the welcomeVideoSurfaceHolder
        welcomeVideoSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                // Create A New Media Player
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDisplay(welcomeVideoSurfaceHolder);

                //try and set the player source
                try {
                    mediaPlayer.setDataSource(welcomeVideoURL);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(WelcomeActivity.this);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }



    // On Prepare : Play the video when the media player is ready
    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    // Release MediaPlayer : Releases the media player
    private void releaseMediaPlayer(){
        if (mediaPlayer != null){
            mediaPlayer.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}
