package com.wapazock.doozby.CreateAccountPage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreatePasswordActivityViewModel extends ViewModel {

    // Variables
    private MutableLiveData<Boolean> mValidPassword;

    //Initialization function
    private void init(){
        //initialize mValidPassword
        if (mValidPassword == null){
            mValidPassword = new MutableLiveData<>();
        }
    }

    // Submit Password : Receives password from the activity and validates
    //      if valid, posts a true to the mValidPassword live set
    public void submitPassword(String password){
        //initialize
        init();

        //check if password is valid
        if (password.length() >= 6){
            mValidPassword.postValue(true);
        }
        else {
            mValidPassword.postValue(false);
        }
    }

    // Valid Password : Returns the Mutable dataset mValidPassword
    public LiveData<Boolean> validPassword(){
        init();
        return mValidPassword;
    }

}
