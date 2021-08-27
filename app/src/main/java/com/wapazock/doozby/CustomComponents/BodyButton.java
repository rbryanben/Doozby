package com.wapazock.doozby.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wapazock.doozby.R;

import org.w3c.dom.Attr;

public class BodyButton extends FrameLayout {

    // View
    TextView buttonTextView;
    ProgressBar buttonProgressBar;

    // Variables
    private String previousText;
    private Boolean buttonIsLoading = false;

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
        buttonProgressBar = getRootView().findViewById(R.id.bodyButtonProgressBar);

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
            previousText = text;
        }

        // Set enabled
        Boolean enabled = attributesArray.getBoolean(R.styleable.BodyButton_body_button_enabled,true);
        buttonTextView.setEnabled(enabled);
    }

    // Enable button
    public void enableButton(){
        //if button is loading don't do anything
        if (buttonIsLoading){
            return;
        }

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

    // Set Loading State
    public void setLoading(Boolean loading){
        // Set Button Loading
        if (loading){
            buttonTextView.setText("");
            buttonProgressBar.setVisibility(VISIBLE);
            buttonTextView.setEnabled(false);

            // Set Button is loading to prevent change re-enabling the button
            buttonIsLoading = true;
        }
        else {
            buttonTextView.setText(previousText);
            buttonProgressBar.setVisibility(INVISIBLE);
            buttonTextView.setEnabled(true);

            // Set Button is loading to allow change disabling of button
            buttonIsLoading = false;
        }
    }
}
