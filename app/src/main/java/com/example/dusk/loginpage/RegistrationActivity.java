package com.example.dusk.loginpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
RegistrationActivity
** Registers a User into the application
* */
public class RegistrationActivity extends AppCompatActivity {

    private EditText username_input, email_input, password_input,fullname_input;
    private Button Submit, Reset;
    private TextView Signup_Message;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DbHelper(this);

        username_input = findViewById(R.id.username_input);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        fullname_input = findViewById(R.id.fullname_input);
        Submit = findViewById(R.id.Submit);
        Reset = findViewById(R.id.Reset);

        Submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          String un = username_input.getText().toString();
                                          String email = email_input.getText().toString();
                                          String pwd = password_input.getText().toString();
                                          String fullname = fullname_input.getText().toString();
                                          if (un.isEmpty() || email.isEmpty() || pwd.isEmpty() || fullname.isEmpty()) {
                                              if (un.isEmpty()) {
                                                  username_input.setError("Please enter username");
                                              }
                                              if (email.isEmpty()) {
                                                  email_input.setError("Please enter email");
                                              }
                                              if (pwd.isEmpty()) {
                                                  password_input.setError("Please enter password");
                                              }
                                              if (fullname.isEmpty()) {
                                                  fullname_input.setError("Please enter Fullname");
                                              }
                                          }
                                          else {
                                                  if (db.registrationverification(un)) {
                                                      Accounts acc = new Accounts(un, pwd, email, fullname);
                                                      db.addUser(acc);
                                                      Toast.makeText(getApplicationContext(), "Your Account has been created", Toast.LENGTH_SHORT).show();
                                                      Intent intent = new Intent(RegistrationActivity.this, LoginPageActivity.class);
                                                      startActivity(intent);
                                                  } else {

                                                      username_input.setError("Username Already Taken");
                                                      Toast.makeText(getApplicationContext(), "Error, Username Already Exists", Toast.LENGTH_SHORT).show();
                                                  }
                                              }
                                          }
            });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_input.getText().clear();
                email_input.getText().clear();
                password_input.getText().clear();
                fullname_input.getText().clear();
            }
        });
        username_input.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                username_input.setTextColor(Color.BLACK);
            }
        });

    }
}
