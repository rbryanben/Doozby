package com.wapazock.doozby.Repository;

import com.wapazock.doozby.Utils.Codes;

public interface CreateNewAccountInterface {
    void createAccountResults(Boolean wasSuccessful, Codes result, String serverResponse);
}
