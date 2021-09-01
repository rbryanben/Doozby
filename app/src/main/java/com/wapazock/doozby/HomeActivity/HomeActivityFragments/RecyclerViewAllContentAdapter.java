package com.wapazock.doozby.HomeActivity.HomeActivityFragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.MovieDetails.MovieDetailsActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.BlurredImageHelper;
import com.wapazock.doozby.Utils.Codes;
import com.wapazockdemo.winterstoreconnector.utils.WinterstoreImagesLoaders;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.logging.Handler;

import spencerstudios.com.bungeelib.Bungee;

public class RecyclerViewAllContentAdapter extends RecyclerView.Adapter<RecyclerViewAllContentAdapter.ViewHolder> {

    // Variables
    ArrayList<Movie> MOVIES;
    Context CONTEXT;

    // Constructor
    public RecyclerViewAllContentAdapter(ArrayList<Movie> MOVIES, Context context) {
        this.MOVIES = MOVIES;
        this.CONTEXT  = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        // Inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // View
        View view = inflater.inflate(R.layout.recycler_layout_movie_single,parent,false);

        // Return a new view holder
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // Get Data To Set
        Movie currentMovie = MOVIES.get(position);

        // Set OnClickListener
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start movie activity
                Intent movieActivity = new Intent(CONTEXT,MovieDetailsActivity.class);
                // Parse data
                movieActivity.putExtra("movie",MOVIES.get(position));

                //start activity
                CONTEXT.startActivity(movieActivity);
                Bungee.slideLeft(CONTEXT);
            }
        });
        //load Image
        loadImage(holder,currentMovie,0);
    }

    // Load Image
    private void loadImage(ViewHolder holder, Movie currentMovie,int  RECURSE_COUNT){
        //retry handler
        android.os.Handler retryHandler = new android.os.Handler();

        WinterstoreImagesLoaders imageLoader = new WinterstoreImagesLoaders(DoozbyRepository.getStorageTOKEN(),CONTEXT);
        imageLoader.loadImageAndCache(holder.movieImage, currentMovie.getCoverImageID(), R.drawable.plain_black_background, new WinterstoreImagesLoaders.ImageLoaderInterface() {
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
                            loadImage(holder,currentMovie, RECURSE_COUNT + 1);
                        }
                    },2000);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return MOVIES.size();
    }

    // Recycler View View Holder Class
    public class ViewHolder extends RecyclerView.ViewHolder {

        //View
        private  ImageView movieImage ;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            // View
            movieImage = itemView.findViewById(R.id.recyclerViewSingleImage);
        }

        // Methods

        public ImageView getMovieImage() {
            return movieImage;
        }
    }
}
