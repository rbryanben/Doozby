package com.wapazock.doozby.CreateAccountPage;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wapazock.doozby.MainActivity;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.CheckUsernameInterface;
import com.wapazock.doozby.Utils.Codes;

public class CreateUsernameActivityViewModel extends ViewModel {

    // Variables
    private MutableLiveData<Codes> mUsernameErrorCode;
    private MutableLiveData<Boolean> mUsernameValidUsername;


    // initialize view model
    private void init(){
        //initialize mUsernameResult
        if (mUsernameErrorCode == null){
            mUsernameErrorCode = new MutableLiveData<>();
        }

        //initialize mUsernameValidUsername
        if (mUsernameValidUsername == null){
            mUsernameValidUsername = new MutableLiveData<>();
        }
    }

    // Get Username Error : Returns username error if any
    public LiveData<Codes> getUsernameError(){
        //initialize
        init();

        // return error
        return mUsernameErrorCode;
    }

    // Submit Username Text : Receives text from the Username Input
    //      and checks if valid
    public void submitUsername(String username){

        // If username is >= 6 chars then check the username
        if (username.length() >= 6){
            //Check if username exists
            DoozbyRepository.getInstance().checkUsernameExists(username, new CheckUsernameInterface() {
                @Override
                public void checkUsernameResult(Codes result) {
                    //switch the result
                    switch (result){
                        //Username taken
                        case USERNAME_TAKEN:
                            // Update that the username is taken
                            mUsernameValidUsername.postValue(false);
                            mUsernameErrorCode.postValue(Codes.USERNAME_TAKEN);
                            break;
                        case USERNAME_VALID:
                            // Update the valid username value
                            mUsernameValidUsername.postValue(true);
                            break;
                    }
                }
            });
        }
        // Username is shorter than 6 chars
        else {
            mUsernameValidUsername.postValue(false);
        }
    }


    // Get Valid Username : Returns True if the username is valid
    public LiveData<Boolean> isValidUsername(){
        init();
        return mUsernameValidUsername;
    }
}
