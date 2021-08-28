package com.wapazock.doozby.CreateAccountPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.wapazock.doozby.Classes.Credentials;
import com.wapazock.doozby.CustomComponents.BodyButton;
import com.wapazock.doozby.CustomComponents.BodyInput;
import com.wapazock.doozby.CustomComponents.Toasts;
import com.wapazock.doozby.MainActivity;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.Codes;

import spencerstudios.com.bungeelib.Bungee;


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

        // Watch for account created
        bindAccountCreated();

        // Watch for error
        bindErrors();

        // Bind Next Button
        bindNextButton();
    }

    // Bind Errors : Watches if an error occurred
    //      creates a notification to notify the client
    private void bindErrors() {
        //watch errors
        activityViewModel.errorOccurred().observe(this, new Observer<Codes>() {
            @Override
            public void onChanged(Codes codes) {
                //stop loading
                nextBodyButton.setLoading(false);

                switch (codes){
                    case CONNECTION_ERROR:
                        Toasts.showWarningSilentToast("Connection Error",CreatePasswordActivity.this);
                        break;
                    case REQUEST_FAILED:
                        Toasts.showWarningSilentToast("Server Error",CreatePasswordActivity.this);
                        break;
                }
            }
        });
    }

    // Bind Account Created : Watches if account is created
    //      if created, stops button loading and proceeds to the next acivity
    private void bindAccountCreated() {
        activityViewModel.accountCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                //If Created
                if (aBoolean){
                    nextBodyButton.setLoading(false);
                    //go to next activity
                    Intent mainActivityIntent = new Intent(CreatePasswordActivity.this, MainActivity.class);
                    mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //start the activity
                    CreatePasswordActivity.this.startActivity(mainActivityIntent);
                    Bungee.slideLeft(CreatePasswordActivity.this);
                    CreatePasswordActivity.this.finish();
                }
            }
        });
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
        // Request Focus
        passwordBodyInput.requestFocus();

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

    // Bind Next Button
    private void bindNextButton(){
        nextBodyButton.getButtonTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get username from extras
                String username =CreatePasswordActivity.this.getIntent().getStringExtra("username");

                //check that username is not null
                if (username == null){ return; }

                //create a Credentials object to create account with
                Credentials newAccountCredentials = new Credentials(username,passwordBodyInput.getEditText().getText().toString());

                //set the button to loading
                nextBodyButton.setLoading(true);

                //submit new credentials
                activityViewModel.submitNewCredentials(newAccountCredentials);
            }
        });
    }


}