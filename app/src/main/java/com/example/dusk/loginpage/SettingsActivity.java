package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    //Object Variables
    EditText username;
    EditText password;
    EditText newpassword;
    EditText email;

    Button btnCancel;
    Button btnSave;
    DbHelper OrganizeMyLifeDB;



        username = findViewById(R.id.nameText);
        password = findViewById(R.id.passwordText);
        newpassword = findViewById(R.id.newPasswordText);
        email = findViewById(R.id.emailText);


        btnCancel = findViewById(R.id.cancelButton);
        btnSave = findViewById(R.id.saveButton);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * onClick Action for @code btnLogin
             * If the inputed userID and password entered for
             * @code username and @code password is valid, this will
             * bring the user to the main app page, or else it will
             * prompt a toast to the user of invalid login credentials
             */
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        getIntent();
    }




}
