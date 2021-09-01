package com.wapazock.doozby.MovieDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.HomeActivity.HomeActivityFragments.RecyclerViewAllContentAdapter;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.LayoutHelper;
import com.wapazockdemo.winterstoreconnector.utils.WinterstoreImagesLoaders;

public class MovieDetailsActivity extends AppCompatActivity {

    // Variables
    private Movie MOVIE;

    // View
    private ImageView movieDetailsImageView;
    private TextView movieDetailsNameTextView, movieDetailsYearTextView, movieDetailsCategoryTextView
            , movieDetailsDescription;

    private VideoView movieDetailsVideoView;
    private ProgressBar movieDetailsVideoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // View
        movieDetailsImageView = findViewById(R.id.movieDetailsActivityImageView);
        movieDetailsNameTextView = findViewById(R.id.movieDetailsActivityNameTextView);
        movieDetailsYearTextView = findViewById(R.id.activityMovieYearTextView);
        movieDetailsCategoryTextView = findViewById(R.id.activityMovieCategoryTextView);
        movieDetailsDescription = findViewById(R.id.activityMovieDescriptionTextView);
        movieDetailsVideoView = findViewById(R.id.activityMovieTrailerVideoView);
        movieDetailsVideoProgressBar = findViewById(R.id.activityMovieTrailerProgressBar);

        // Set fullscreen and translucent
        LayoutHelper.setTranslucent(MovieDetailsActivity.this);
        LayoutHelper.removeLimits(MovieDetailsActivity.this);

        // Bind with View Model

        // Assign the MOVIE
        assignMovie();

        // Assign Trailer
        assignTrailer();
    }

    // Assigns the movie that was parsed
    private void assignMovie() {
        // Assign MOVIE
        MOVIE = getIntent().getParcelableExtra("movie");

        // If MOVIE is null close the activity
        if (MOVIE == null){
            MovieDetailsActivity.this.finish();
        }

        // Set Name
        movieDetailsNameTextView.setText(MOVIE.getName());

        // Set Year
        movieDetailsYearTextView.setText(MOVIE.getYear());

        // Set Category
        movieDetailsCategoryTextView.setText(MOVIE.getCategory());

        // Set Description
        movieDetailsDescription.setText(MOVIE.getDescription());

        // Set the movie image
        loadImage(26);
    }

    // Sets the trailer
    private void assignTrailer() {
        // Set Video View Source
        movieDetailsVideoView.setVideoPath("http://192.168.1.5/api/open-stream/" + MOVIE.getMovieTrailerID());

        // Set Media Controller
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(movieDetailsVideoView);
        movieDetailsVideoView.setMediaController(mediaController);

        // Disable Progress Bar
        movieDetailsVideoProgressBar.setVisibility(View.GONE);
    }

    // Load Image
    private void loadImage(int RECURSE_COUNT){
        //retry handler
        android.os.Handler retryHandler = new android.os.Handler();

        WinterstoreImagesLoaders imageLoader = new WinterstoreImagesLoaders(DoozbyRepository.getStorageTOKEN(),
                MovieDetailsActivity.this);
        imageLoader.loadImageAndCache(movieDetailsImageView, MOVIE.getCoverImageID(), R.drawable.plain_black_background, new WinterstoreImagesLoaders.ImageLoaderInterface() {
            @Override
            public void result(Boolean wasSuccessful, String imageID) {
                if (!wasSuccessful){
                    if (RECURSE_COUNT == 26){
                        return;
                    }
                    //recurse on a new handler if failed
                    retryHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadImage(RECURSE_COUNT + 1);
                        }
                    },2000);
                }
            }
        });
    }

}