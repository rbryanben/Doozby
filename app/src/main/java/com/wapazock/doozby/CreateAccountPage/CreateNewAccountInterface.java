package com.wapazock.doozby.CreateAccountPage;

import com.wapazock.doozby.Utils.Codes;

public interface CreateNewAccountInterface {
    void createAccountResults(Boolean wasSuccessful, Codes result, String serverResponse);
}
