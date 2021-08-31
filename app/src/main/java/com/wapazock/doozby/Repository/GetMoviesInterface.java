package com.wapazock.doozby.Repository;

import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.Utils.Codes;

import java.util.ArrayList;

public interface GetMoviesInterface {
    void result(Boolean wasSuccessful, Codes code, ArrayList<Movie> MOVIES);
}
