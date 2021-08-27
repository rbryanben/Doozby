package com.wapazock.doozby.CreateAccountPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.wapazock.doozby.CustomComponents.BodyInput;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.Codes;

public class CreateUsernameActivity extends AppCompatActivity {
    // TAG
    public static String TAG = "Create Username Activity";

    // View
    BodyInput usernameBodyInput;

    // Variables
    CreateUsernameActivityViewModel activityViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_username);

        // View
        usernameBodyInput = findViewById(R.id.activityCreateUsernameUsernameBodyInput);

        // Set View Model
        activityViewModel = new ViewModelProvider(this).get(CreateUsernameActivityViewModel.class);


        // Bind Username Body Input with this class
        bindUsernameBodyInput();

        // Bind Username errors with this class
        bindUsernameErrors();
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
                //Switch the codes
                switch (codes){
                    case CONNECTION_ERROR:
                        Toast.makeText(CreateUsernameActivity.this,"Network Error",Toast.LENGTH_SHORT).show();
                        break;
                    case USERNAME_TAKEN:
                        Toast.makeText(CreateUsernameActivity.this,"Username Taken",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}