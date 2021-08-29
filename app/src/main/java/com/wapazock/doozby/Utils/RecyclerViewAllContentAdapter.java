package com.wapazock.doozby.Utils;

import android.app.Activity;
import android.content.Context;
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
import com.wapazock.doozby.R;
import com.wapazockdemo.winterstoreconnector.interfaces.ConnectionInterface;
import com.wapazockdemo.winterstoreconnector.utils.Connection;
import com.wapazockdemo.winterstoreconnector.utils.Credentials;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;

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

        // Dummy Token
        String TOKEN = "86efc06c6b5d28dfd6cd5683d2139569208c271e";


        loadImage(holder.getMovieImage(),currentMovie.getCoverImageID(),TOKEN);
    }

    // Load Image: Given an object to display the image, assigns the given
    // imageID as the image received from the server
    public void loadImage(ImageView imageView, String imageID, String Token){
        // Custom Headers for Glide Request
        GlideUrl url = new GlideUrl("https://cloudwinterstore.co.zw/api/download/" + imageID, new LazyHeaders.Builder()
                .addHeader("Authorization", "Token " + Token)
                .build());

        // Load the image
        Glide.with(CONTEXT)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
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
