package com.wapazock.doozby.HomeActivity.HomeActivityFragments;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.Repository.DoozbyRepository;
import com.wapazock.doozby.Repository.GetMoviesInterface;
import com.wapazock.doozby.Utils.Codes;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllContentFragmentViewModel extends AndroidViewModel {

    // Variables
    private MutableLiveData<ArrayList<Movie>> mMovies;
    private MutableLiveData<Codes> mErrors;

    // Data sets
    private ArrayList<Movie> MOVIES_LIST = new ArrayList<>();

    public AllContentFragmentViewModel(@NonNull @NotNull Application application) {
        super(application);
    }

    // Initialization method
    private void init(){
        //initialize mMovies
        if (mMovies == null){
            mMovies = new MutableLiveData<>();
            // Get movies
            DoozbyRepository.getInstance().getMovies(0, new GetMoviesInterface() {
                @Override
                public void result(Boolean wasSuccessful, Codes code, ArrayList<Movie> MOVIES) {
                    // Join the 2 arrays
                    if (wasSuccessful){
                        MOVIES_LIST.addAll(MOVIES);
                        mMovies.postValue(MOVIES);
                        return;
                    }
                    // An error occurred
                    mErrors.postValue(code);
                }
            });
        }

        // initialize mErrors
        if (mErrors == null){
            mErrors = new MutableLiveData<>();
        }
    }


    // Get Movies : Returns movies live data
    public LiveData<ArrayList<Movie>> getMovies(){
        //initialize view model
        init();
        // Add data set
        mMovies.setValue(MOVIES_LIST);
        // return movies list
        return mMovies;
    }

    // Gte Errors : Returns errors if any
    public LiveData<Codes> getErrors(){
        //initialize view model
        init();

        //return errors
        return mErrors;
    }

}
