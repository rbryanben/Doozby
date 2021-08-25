package com.wapazock.doozby.WelcomePage;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wapazock.doozby.R;

public class WelcomeActivityViewModel extends ViewModel {

    // Welcome Video URL
    private String welcomeVideoURL = "http://192.168.1.5/api/open-stream/BOJX10NQ8PF75JLOTFCRKQONT9W8SDQCPLM6QYUNJWDTS2CW3JU50XOAHT2DPIDP";


    // Variables
    MediaPlayer welcomeMediaPlayer;

    // Initialization Function
    private void init(){

        // Initialize Media Player
        if (welcomeMediaPlayer == null) {
            initWelcomeMediaPlayer();
        }

    }

    // Initialize Welcome Media Player : Initializes the welcome media player
    private void initWelcomeMediaPlayer(){
        //initialize a media player
        welcomeMediaPlayer = new MediaPlayer();

        // Set Data Source
        try {
            welcomeMediaPlayer.setDataSource(welcomeVideoURL);
            welcomeMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            welcomeMediaPlayer.prepare();
            welcomeMediaPlayer.setLooping(true);
            welcomeMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    welcomeMediaPlayer.start();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    // Get Media Player : Returns a live MediaPlayer
    public LiveData<MediaPlayer> getMediaPlayer(){
         //initialize
        init();

        // Mutable Live Data to return
        MutableLiveData mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(welcomeMediaPlayer);

        //return the mutable live data
        return mutableLiveData;
    }

    // Activity Pause or Destroyed : Release the Media Player when the activity has stopped
    public void activityStopped(){
        releaseMediaPlayer();
    }

    // Release MediaPlayer : Releases the media player
    private void releaseMediaPlayer(){
        if (welcomeMediaPlayer != null){
            welcomeMediaPlayer.release();
        }
    }

}

