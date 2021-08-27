package com.wapazock.doozby.CreateAccountPage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.CheckUsernameInterface;
import com.wapazock.doozby.Utils.Codes;

public class CreateUsernameActivityViewModel extends ViewModel {

    // Variables
    MutableLiveData<Codes> mUsernameResult;


    // initialize view model
    private void init(){
        //initialize mUsernameResult
        if (mUsernameResult == null){
            mUsernameResult = new MutableLiveData<>();
        }
    }

    // Get Username Error : Returns username error if any
    public LiveData<Codes> getUsernameError(){
        //initialize
        init();

        // return error
        return mUsernameResult;
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
                            mUsernameResult.postValue(Codes.USERNAME_TAKEN);
                            break;
                    }
                }
            });
        }
    }

}
