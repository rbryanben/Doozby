package com.wapazock.doozby.CreateAccountPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wapazock.doozby.CustomComponents.BodyButton;
import com.wapazock.doozby.CustomComponents.BodyInput;
import com.wapazock.doozby.CustomComponents.Toasts;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.Codes;

import java.util.zip.Inflater;

import spencerstudios.com.bungeelib.Bungee;


public class CreateUsernameActivity extends AppCompatActivity {
    // TAG
    public static String TAG = "Create Username Activity";

    // View
    BodyInput usernameBodyInput;
    BodyButton nextButton;

    // Variables
    CreateUsernameActivityViewModel activityViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_username);

        // View
        usernameBodyInput = findViewById(R.id.activityCreateUsernameUsernameBodyInput);
        nextButton = findViewById(R.id.activityCreateUsernameActivityNextBodyButton);

        // Set View Model
        activityViewModel = new ViewModelProvider(this).get(CreateUsernameActivityViewModel.class);


        // Bind Username Body Input with this class
        bindUsernameBodyInput();

        // Bind Next Button
        bindNextButton();

        // Bind Username errors with this class
        bindUsernameErrors();

        // Bind Username Valid
        bindUsernameValid();

    }

    // Bind Next Button : Sets a click listener on the next button
    //      on click, starts a new activity
    private void bindNextButton() {
        nextButton.getButtonTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity on click
                Intent createPasswordIntent = new Intent(CreateUsernameActivity.this,CreatePasswordActivity.class);
                createPasswordIntent.putExtra("username",usernameBodyInput.getEditText().getText().toString());
                CreateUsernameActivity.this.startActivity(createPasswordIntent);
                Bungee.slideLeft(CreateUsernameActivity.this);
            }
        });
    }

    // Binds the username input with this class
    //      Adds listener to the text input, after typing more than 6 chars will submit
    //           the username for checking
    private void bindUsernameBodyInput() {
        //set on text changed listener
        usernameBodyInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Submit the username
                activityViewModel.submitUsername(usernameBodyInput.getEditText().getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }


    // Bind Username Errors : Bind an error from the View Model
    private void bindUsernameErrors(){
        activityViewModel.getUsernameError().observe(this, new Observer<Codes>() {
            @Override
            public void onChanged(Codes codes) {
                // Disable next button
                nextButton.getButtonTextView().setEnabled(false);

                //Switch the codes
                switch (codes){
                    case CONNECTION_ERROR:
                        Toasts.showWarningSilentToast("Connection Error",CreateUsernameActivity.this);
                        break;
                    case USERNAME_TAKEN:
                        Toasts.showWarningSilentToast("Username Taken",CreateUsernameActivity.this);
                        break;
                }
            }
        });
    }

    // Bind Valid Username : Observe if the username becomes valid
    //      enable next button if valid
    private void bindUsernameValid(){
        activityViewModel.isValidUsername().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // If true enable the button, else disable it
                if (aBoolean){
                    nextButton.enableButton();
                }
                else {
                    nextButton.disableButton();
                }
            }
        });
    }

    // Activity Stopped -- alert view model
    @Override
    protected void onStop() {
        super.onStop();
        activityViewModel.activityStopped();
    }

    // Activity Paused -- alert view model
    @Override
    protected void onPause() {
        super.onPause();
        activityViewModel.activityStopped();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityViewModel.activityResumed();
    }
}