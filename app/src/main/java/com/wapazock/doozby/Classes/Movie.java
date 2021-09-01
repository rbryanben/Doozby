package com.wapazock.doozby.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {

    // Variables
    private String coverImageID, name, year, category, description, movieID, movieTrailerID;


    // Constructor with JSONObject
    public Movie(JSONObject jsonObject){
        try{
            this.coverImageID = jsonObject.getString("coverImageID");
            this.name = jsonObject.getString("name");
            this.year = jsonObject.getString("year");
            this.category = jsonObject.getString("category_name");
            this.description = jsonObject.getString("description");
            this.movieID = jsonObject.getString("movieID");
            this.movieTrailerID = jsonObject.getString("movieTrailerID");

        }
        catch (Exception e){
            // print stack trace
            e.printStackTrace();
            this.coverImageID = "none";
            this.name = "name" ;
        }
    }


    protected Movie(Parcel in) {
        coverImageID = in.readString();
        name = in.readString();
        year = in.readString();
        category = in.readString();
        description = in.readString();
        movieID = in.readString();
        movieTrailerID = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //methods
    public String getCoverImageID() {
        return coverImageID;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovieTrailerID() {
        return movieTrailerID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coverImageID);
        dest.writeString(name);
        dest.writeString(year);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(movieID);
        dest.writeString(movieTrailerID);
    }
}
