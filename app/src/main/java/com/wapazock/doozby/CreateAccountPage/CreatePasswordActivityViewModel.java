package com.wapazock.doozby.CreateAccountPage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wapazock.doozby.Classes.Credentials;
import com.wapazock.doozby.Repository.CreateNewAccountInterface;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Utils.Codes;

import org.jetbrains.annotations.NotNull;

public class CreatePasswordActivityViewModel extends AndroidViewModel {

    // Variables
    private MutableLiveData<Boolean> mValidPassword;
    private MutableLiveData<Boolean> mAccountCreated;
    private MutableLiveData<Codes> mError;

    public CreatePasswordActivityViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

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

                    // Store the token in Shared Preferences
                    SharedPreferences preferences = getApplication().getSharedPreferences("CREDENTIALS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("TOKEN",serverResponse);
                    editor.apply();

                    // Set the TOKEN for the repository
                    DoozbyRepository.setTOKEN(serverResponse);
                }
                // Else update error
                else {
                    mError.postValue(result);
                }
            }
        });
    }

}
