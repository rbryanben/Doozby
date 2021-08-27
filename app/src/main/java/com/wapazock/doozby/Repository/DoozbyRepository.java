package com.wapazock.doozby.Repository;

import com.wapazock.doozby.Utils.CheckUsernameInterface;
import com.wapazock.doozby.Utils.Codes;

import java.util.ArrayList;

public class DoozbyRepository {

    // Static Variables
    public static DoozbyRepository instance;

    // Singleton Repository Implementation
    public static DoozbyRepository getInstance(){
        if (instance == null){
            instance = new DoozbyRepository();
        }
        return instance;
    }

    //Check Username : Checks if the username is taken from the
    //        server
    public void checkUsernameExists(String username, CheckUsernameInterface checkUsernameInterface){
        //dummy list
        ArrayList<String> takenUsernames = new ArrayList<String>();
        takenUsernames.add("rbryanben");
        takenUsernames.add("michael");
        takenUsernames.add("trevor");
        takenUsernames.add("franklin");

        // check if username is in the array
        if (takenUsernames.contains(username)){
            checkUsernameInterface.checkUsernameResult(Codes.USERNAME_TAKEN);
        }
        else {
            checkUsernameInterface.checkUsernameResult(Codes.USERNAME_VALID);
        }
    }

}
