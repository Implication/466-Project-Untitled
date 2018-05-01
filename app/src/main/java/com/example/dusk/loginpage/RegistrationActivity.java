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

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
RegistrationActivity
** Registers a User into the application
* */
public class RegistrationActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\\Q\\?[]{}.\\E#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
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
        //We want to submit the changes we made to registration page to the database
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String un = username_input.getText().toString();
                String email = email_input.getText().toString();
                String pwd = password_input.getText().toString();
                String fullname = fullname_input.getText().toString();
                //We check to see if any of the settigns are empty
                Matcher e = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                Matcher p = VALID_PASSWORD_REGEX.matcher(pwd);
                if (un.isEmpty() || email.isEmpty() || pwd.isEmpty() || fullname.isEmpty() || !p.find() || !e.find()) {
                    if (un.isEmpty()) {
                        username_input.setError("Please enter username");
                    }
                    if (!e.find()) {
                        email_input.setError("Please enter a proper email address");
                    }
                    if (pwd.isEmpty()) {
                        password_input.setError("Please enter password");
                    }
                    if (fullname.isEmpty()) {
                        fullname_input.setError("Please enter Fullname");
                    }
                    if (!p.find()) {
                        password_input.setError("Password must have, at least 8 chracters, 1 uppercase letter, and 1 special character");
                    }

                }
                else {
                    //We check to see if the user is in the system
                    if (db.registrationverification(un)) {
                        Accounts acc = new Accounts(un, pwd, email, fullname);
                        db.addUser(acc);
                        StyleableToast.makeText(getApplicationContext(), "Your account has been created", R.style.toastTheme).show();
                        Intent intent = new Intent(RegistrationActivity.this, LoginPageActivity.class);
                        startActivity(intent);
                    } else {

                        username_input.setError("Username Already Taken");
                        StyleableToast.makeText(getApplicationContext(), "Error: Username already exists", R.style.toastTheme).show();
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
