package com.wapazock.doozby.Repository;

import com.wapazock.doozby.Utils.Codes;

public interface FetchStorageTokenInterface {
    void result(Boolean wasSuccessful, Codes code,String result);
}
