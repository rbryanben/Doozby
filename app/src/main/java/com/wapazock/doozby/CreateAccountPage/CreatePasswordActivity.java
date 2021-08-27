package com.wapazock.doozby.CreateAccountPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.wapazock.doozby.CustomComponents.BodyButton;
import com.wapazock.doozby.CustomComponents.BodyInput;
import com.wapazock.doozby.R;

public class CreatePasswordActivity extends AppCompatActivity {

    // View
    BodyInput passwordBodyInput;
    BodyButton nextBodyButton;

    // Variables
    CreatePasswordActivityViewModel activityViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        // Bind View
        passwordBodyInput = findViewById(R.id.activityCreatePasswordPasswordInput);
        nextBodyButton = findViewById(R.id.activityCreatePasswordNextButton);

        // Bind View Model
        activityViewModel = new ViewModelProvider(this).get(CreatePasswordActivityViewModel.class);

        // Bind Password Input
        bindPasswordInput();

        // Watch for valid password
        bindValidPassword();
    }

    // Bind Valid Password : Watches for a valid password
    //      if password is valid, enables next button
    private void bindValidPassword() {
        activityViewModel.validPassword().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // If Valid
                if (aBoolean){
                    nextBodyButton.enableButton();
                }
                //invalid password
                else {
                    nextBodyButton.disableButton();
                }
            }
        });
    }

    // Bind Password Input : Sets an on text change listener on the password
    //      when the client enters a word, submits the password for checking in the
    //      view model
    private void bindPasswordInput() {
        // Set on text changed listener
        passwordBodyInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //submit the password for assessment
                activityViewModel.submitPassword(passwordBodyInput.getEditText().getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}