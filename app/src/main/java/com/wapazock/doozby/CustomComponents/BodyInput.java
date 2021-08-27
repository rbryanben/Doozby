package com.wapazock.doozby.CustomComponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wapazock.doozby.R;

public class BodyInput extends FrameLayout {
    // View
    private EditText editText ;

    public BodyInput(@NonNull Context context) {
        super(context);
        init(null);
    }

    public BodyInput(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BodyInput(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    // Initialization Function
    private void init(AttributeSet attrs){
        //inflate layout
        inflate(getContext(), R.layout.body_input_layout,this);

        //view
        editText = getRootView().findViewById(R.id.bodyInputEditText);

        // Set attributes
        setAttributes(attrs);
    }

    // Binds the attributes with that of the view components
    private void setAttributes(AttributeSet attrs) {
        if (attrs == null){
            return;
        }


        //add attributes into a typed array
        TypedArray attributesArray = getContext().obtainStyledAttributes(attrs,R.styleable.BodyInput);

        // Hint
        String textHint = attributesArray.getString(R.styleable.BodyInput_body_input_hint);

        if (textHint != null){
            editText.setHint(textHint);
        }

        // Password
        Boolean isPassword = attributesArray.getBoolean(R.styleable.BodyInput_body_input_is_password,false);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }


    public EditText getEditText() {
        return editText;
    }
}
