package com.wapazock.doozby.CreateAccountPage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wapazock.doozby.Classes.Credentials;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.Codes;
import com.wapazock.doozby.Utils.CreateNewAccountInterface;

public class CreatePasswordActivityViewModel extends ViewModel {

    // Variables
    private MutableLiveData<Boolean> mValidPassword;
    private MutableLiveData<Boolean> mAccountCreated;
    private MutableLiveData<Codes> mError;

    //Initialization function
    private void init(){
        //initialize mValidPassword
        if (mValidPassword == null){
            mValidPassword = new MutableLiveData<>();
        }

        //initialize mAccountCreated
        if (mAccountCreated == null){
            mAccountCreated = new MutableLiveData<>();
        }

        // initialize mError
        if (mError == null){
            mError = new MutableLiveData<>();
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

    // Account Created : Returns Mutable
    //      Usage : When the account creation is successful
    public LiveData<Boolean> accountCreated(){
        init();
        return mAccountCreated;
    }

    // Error Created : Returns mutable
    //      Usage : Connection error occurred
    public LiveData<Codes> errorOccurred(){
        init();
        return mError;
    }

    // Submit New Credentials : Receives new credentials to create an account with
    public void submitNewCredentials(Credentials credentials){
        DoozbyRepository.getInstance().createNewAccount(credentials, new CreateNewAccountInterface() {
            @Override
            public void createAccountResults(Boolean wasSuccessful, Codes result, String serverResponse) {
                // If Successful update accountCreated live data
                if (wasSuccessful) {
                    mAccountCreated.postValue(true);
                }
                // Else update error
                else {
                    mError.postValue(result);
                }
            }
        });
    }

}
