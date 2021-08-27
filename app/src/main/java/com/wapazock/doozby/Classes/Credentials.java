package com.wapazock.doozby.Classes;

import org.json.JSONObject;

public class Credentials {

    // Variables
    private String username, password ;

    //constructor
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //returns objects as JSON Object
    public JSONObject asJSONObject(){
        //create a new JSON Object
        JSONObject credentialsJSON = new JSONObject();

        //insert items into object
        try {
            credentialsJSON.put("username", this.getUsername());
            credentialsJSON.put("password", this.getPassword());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return  credentialsJSON;
    }

    // Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
