package com.wapazock.doozby.HomeActivity;

import com.wapazock.doozby.Utils.Codes;

public interface FetchStorageTokenInterface {
    void result(Boolean wasSuccessful, Codes code,String result);
}
