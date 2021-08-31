package com.wapazock.doozby.Classes;

import org.json.JSONObject;

public class Movie {

    // Variables
    private String coverImageID;
    private String name;


    // Constructor
    public Movie(String coverImageID){
        this.coverImageID = coverImageID;
    }

    // Constructor with JSONObject
    public Movie(JSONObject jsonObject){
        try{
            this.coverImageID = jsonObject.getString("coverImageID");
            this.name = jsonObject.getString("name");
        }
        catch (Exception e){
            // print stack trace
            e.printStackTrace();
            this.coverImageID = "none";
            this.name = "name" ;
        }
    }

    //methods
    public String getCoverImageID() {
        return coverImageID;
    }
}
