package com.wapazock.doozby.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wapazock.doozby.R;

public class CircularTransparentButton extends FrameLayout {

    // Variables
    TextView textView;


    public CircularTransparentButton(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CircularTransparentButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircularTransparentButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet){
        inflate(getContext(), R.layout.circular_button_layout,this);
        // Assign Variables
        textView = getRootView().findViewById(R.id.circularTransparentButtonTextView);

        //set attributes
        setAttributes(attributeSet);
    }

    // Sets The Attributes for the object
    private void setAttributes(AttributeSet attributeSet) {
        //check if there are attributes
        if (attributeSet == null)
            return; //break because there are no attributes

        //add attributes into a typed array
        TypedArray attributesArray = getContext().obtainStyledAttributes(attributeSet,R.styleable.CircularTransparentButton);

        // Set Text
        String text = attributesArray.getString(R.styleable.CircularTransparentButton_text);

        if (text != null){
            textView.setText(text);
        }
    }


}
