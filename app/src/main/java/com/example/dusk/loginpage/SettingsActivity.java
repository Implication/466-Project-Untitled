package com.example.dusk.loginpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
    //Object Variables
    EditText username;
    EditText password;
    EditText newpassword;
    EditText email;
    EditText phone;

    Button btnCancel;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = findViewById(R.id.nameText);
        password = findViewById(R.id.passwordText);
        newpassword = findViewById(R.id.newPasswordText);
        email = findViewById(R.id.emailText);
        phone = findViewById(R.id.phoneText);

        btnCancel = findViewById(R.id.cancelButton);
        btnSave = findViewById(R.id.saveButton);
        getIntent();
    }




}
