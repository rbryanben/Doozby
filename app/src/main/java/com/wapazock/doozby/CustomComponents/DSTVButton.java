package com.wapazock.doozby.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.wapazock.doozby.R;

public class DSTVButton extends FrameLayout {

    // View
    private ConstraintLayout dstvButtonConstraintLayout;
    private ImageView dstvButtonImageView;
    private TextView dstvButtonTextView;

    public DSTVButton(@NonNull Context context) {
        super(context);
        init(null);
    }

    public DSTVButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DSTVButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    // Init
    private void init(AttributeSet attrs){
        //inflate view
        inflate(getContext(), R.layout.dstv_button_layout,this);

        //view
        dstvButtonConstraintLayout = getRootView().findViewById(R.id.dstvButtonConstraintLayout);
        dstvButtonImageView = getRootView().findViewById(R.id.dstvButtonImageView);
        dstvButtonTextView = getRootView().findViewById(R.id.dstvButtonTextView);

        // Set Attributes
        setAttributes(attrs);
    }

    // Sets Attributes
    private void setAttributes(AttributeSet attrs) {
        // check if attribute set is not null
        if (attrs == null){
            return;
        }

        // Typed Array
        TypedArray attributeArray = getContext().obtainStyledAttributes(attrs,R.styleable.DSTVButton);

        // Set Text
        String text = attributeArray.getString(R.styleable.DSTVButton_dstv_button_text);
        if (text != null){
            dstvButtonTextView.setText(text);
        }

        // Set Image
        int icon = attributeArray.getInteger(R.styleable.DSTVButton_dstv_button_icon,0);
        if (icon != 0){
            dstvButtonImageView.setImageResource(icon);
        }
    }

    //Get Root element
    public ConstraintLayout getRootElement(){
        return dstvButtonConstraintLayout;
    }

}
