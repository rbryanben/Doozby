package com.wapazock.doozby.CustomComponents;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wapazock.doozby.R;

public class Toasts {

    // Show Silent Toast : Shows a silent toast in a layout
    public static void showWarningSilentToast(String caption,Activity activity){
        LayoutInflater inflater = activity.getLayoutInflater();
        View inflateView = inflater.inflate(R.layout.silent_toast_layout, (ViewGroup) activity.findViewById(R.id.silentToastRoot));
        // Set the caption
        ((TextView) inflateView.findViewById(R.id.silentToastCaption)).setText(caption);

        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL,0,0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(inflateView);
        toast.show();
    }
}
