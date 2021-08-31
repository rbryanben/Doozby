package com.wapazock.doozby.HomeActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Repository.FetchStorageTokenInterface;
import com.wapazock.doozby.Utils.Codes;

import org.jetbrains.annotations.NotNull;

public class HomeActivityViewModel extends AndroidViewModel {

    // Constructor
    public HomeActivityViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    // Variables
    private MutableLiveData<Codes> mStorageTokenErrors;
    private MutableLiveData<Boolean> mRepositoryReady;

    //Initialization Function
    private void init(){
        //init mReceivedStorageToken
        if (mStorageTokenErrors == null){
            initStorageToken();
        }

        //init mRepositoryReady
        if (mRepositoryReady == null){
            mRepositoryReady = new MutableLiveData<>();
        }
    }

    // Initializes the storage token
    private void initStorageToken() {
        //instance mutable live data
        mStorageTokenErrors = new MutableLiveData<>();

        // Get the storage token
        DoozbyRepository.getInstance().getStorageToken(new FetchStorageTokenInterface() {
            @Override
            public void result(Boolean wasSuccessful, Codes code, String result) {
                if (!wasSuccessful){
                    mStorageTokenErrors.postValue(code);
                }
                else {
                    mRepositoryReady.postValue(true);
                }
            }
        });
    }

    // Get Errors on inquiring the token
    public LiveData<Codes> getStorageTokenErrors(){
        //initialize the view model
        init();
        return mStorageTokenErrors;
    }

    // Get When Repository is ready
    public LiveData<Boolean> getRepositoryReady(){
        init();
        return  mRepositoryReady;
    }
}
