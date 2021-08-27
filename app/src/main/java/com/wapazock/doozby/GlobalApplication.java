package com.wapazock.doozby;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application {

    //Context
    private Context context;

    // Get Context
    public Context getContext() {
        return context;
    }

    // Set Context
    public void setContext(Context context) {
        this.context = context;
    }
}
