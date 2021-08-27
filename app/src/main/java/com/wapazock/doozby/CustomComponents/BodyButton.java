package com.wapazock.doozby.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wapazock.doozby.R;

import org.w3c.dom.Attr;

public class BodyButton extends FrameLayout {

    // View
    TextView buttonTextView;

    public BodyButton(@NonNull Context context) {
        super(context);
        init(null);
    }

    public BodyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BodyButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    // Initialization Function
    private void init(AttributeSet attrs){
        inflate(getContext(), R.layout.body_button_layout,this);

        // bind views
        buttonTextView = getRootView().findViewById(R.id.bodyButtonTextView);

        setAttributes(attrs);
    }

    // Function to set attributes
    private void setAttributes(AttributeSet attrs){
        // Make sure that the attrs are not null
        if (attrs == null){
            return;
        }

        // Extract the attribute set
        TypedArray attributesArray = getContext().obtainStyledAttributes(attrs,R.styleable.BodyButton);

        // Set the Text View
        String text = attributesArray.getString(R.styleable.BodyButton_body_button_text);
        if (text != null){
            buttonTextView.setText(text);
        }

        // Set enabled
        Boolean enabled = attributesArray.getBoolean(R.styleable.BodyButton_body_button_enabled,true);
        buttonTextView.setEnabled(enabled);
    }

    // Enable button
    public void enableButton(){
        buttonTextView.setEnabled(true);
    }

    // Disable button
    public void disableButton(){
        buttonTextView.setEnabled(false);
    }

    // Get Button
    public TextView getButtonTextView() {
        return buttonTextView;
    }
}
