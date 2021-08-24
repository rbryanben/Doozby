package com.wapazock.doozby.Utils;

import android.app.Activity;
import android.view.WindowManager;

public class LayoutHelper {

    //set a translucent status bar
    public static void setTranslucent(Activity activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    //Removes limits : Removes the boundary limits of a view
    //      Sets fullscreen
    public static void removeLimits(Activity activity){
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }
}
