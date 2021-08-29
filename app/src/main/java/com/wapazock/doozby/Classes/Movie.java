package com.wapazock.doozby.Classes;

public class Movie {

    // Variables
    private String coverImageID;


    // Constructor
    public Movie(String coverImageID){
        this.coverImageID = coverImageID;
    }

    //methods

    public String getCoverImageID() {
        return coverImageID;
    }
}
