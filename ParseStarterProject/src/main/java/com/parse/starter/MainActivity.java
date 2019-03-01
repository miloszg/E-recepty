/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

import static com.google.android.gms.analytics.internal.zzy.v;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {
    Boolean signupModeActive = true;
    TextView loginTextView;
    EditText passwordEditText;
    EditText usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("E-recepty");

        loginTextView=findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);
        usernameEditText= findViewById(R.id.usernameEditText);
        passwordEditText= findViewById(R.id.passwordEditText);
        ImageView logoImage=findViewById(R.id.logoImage);
        RelativeLayout layout=findViewById(R.id.backgroundLayout);
        logoImage.setOnClickListener(this);
        layout.setOnClickListener(this);

        passwordEditText.setOnKeyListener(this);
        if(ParseUser.getCurrentUser()!=null){
             showUserList();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
          signUpClicked(v);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.loginTextView){
            Button signUpButton=findViewById(R.id.signUpButton);
            if(signupModeActive) {
                signupModeActive=false;
                signUpButton.setText("Login");
                loginTextView.setText("or, Sign Up");
            } else {
                signupModeActive=true;
                signUpButton.setText("Sign Up");
                loginTextView.setText("or, Login");
            }
        } else if(view.getId()==R.id.logoImage || view.getId()==R.id.backgroundLayout) {
            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }

    public void showUserList(){
        Intent intent=new Intent(getApplicationContext(),UserMenuActivity.class);
        startActivity(intent);
    }

    public void signUpClicked(View view){
        if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
        } else {
            if(signupModeActive) {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                if (e == null) {
                    Log.i("Sign-up", "Success");
                    showUserList();
                } else {
                  Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
              }
            });
            } else {
                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(parseUser!=null){
                        Log.i("Login","Done");
                        showUserList();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }});
            }
        }
      }

    @Override
    public void onBackPressed() {

    }
}