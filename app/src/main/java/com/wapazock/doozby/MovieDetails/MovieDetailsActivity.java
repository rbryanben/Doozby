package com.wapazock.doozby.MovieDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.LayoutHelper;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Set fullscreen and translucent
        LayoutHelper.setTranslucent(MovieDetailsActivity.this);
        LayoutHelper.removeLimits(MovieDetailsActivity.this);


    }
}