package com.example.dusk.loginpage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/*
RegistrationActivity
** Registers a User into the application
* */
public class RegistrationActivity extends AppCompatActivity {

    private EditText username_input, email_input, password_input;
    private Button Submit, Reset;
    private TextView Signup_Message, username_text, email_text, password_text;
    private ArrayList<Accounts> User_Accounts = new ArrayList<Accounts>(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Signup_Message = findViewById(R.id.Signup_Message);
        username_text = findViewById(R.id.Username);
        email_text = findViewById(R.id.Email);
        password_text = findViewById(R.id.Password);
        username_input = findViewById(R.id.username_input);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        Submit = findViewById(R.id.Submit);
        Reset = findViewById(R.id.Reset);


        Signup_Message.setText("Create your account");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate_Signup(username_input.getText().toString(), email_input.getText().toString(), User_Accounts)) {
                    User_Accounts.add(new Accounts(username_input.getText().toString(), password_input.getText().toString(), email_input.getText().toString()));
                    Signup_Message.setTextColor(Color.BLACK);
                    Signup_Message.setText("Your Account has been created!!");

                } else {
                    Signup_Message.setTextColor(Color.RED);
                    Signup_Message.setText("ERROR!!! Account has already been created!! Change username");
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_input.getText().clear();
                email_input.getText().clear();
                password_input.getText().clear();
            }
        });


    }

    private boolean Validate_Signup (String userName, String email, ArrayList<Accounts> users) {
        for (int i = 0; i < users.size(); ++i) {
            if (userName.equals(users.get(i).get_username()) || email.equals(users.get(i).get_email())) {
                return false;
            }
        }
        return true;
    }


}
