package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Document needs to be edited for the following on a later time
*  - Function to validate username and password from sql database
*  - Update on the UI, based on group input on a later date
*  Made as of 02/07/18 By Trajon Felton
* */

public class LoginPageActivity extends AppCompatActivity {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

    //Object Variables
    DbHelper OrganizeMyLifeDB;
    EditText username;
    EditText password;
    Button forgotPassword;
    Button btnLogin;
    Button btnRegister;


    /**
     * Validates the @code username and @code password texts entered by the user
     * **Note**, for Login Page this will need to be edited to match case by SQL Database.
     *
     * @param emailStr is the email that the user inputed into the UserID textbox
     * @param pwdStr   is the password string that the user inputed into the Password textbox.
     */
    public static boolean validate(String emailStr, String pwdStr) {
        Matcher emailMatch = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        Matcher pwdMatch = VALID_PASSWORD_REGEX.matcher(pwdStr);
        return emailMatch.find() && pwdMatch.find();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        forgotPassword = findViewById(R.id.forgotPasswordButton);
        forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginPageActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        OrganizeMyLifeDB = new DbHelper(this);

        //Object Variables
        username = findViewById(R.id.loginUserID);
        password = findViewById(R.id.loginUserPwd);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegister = findViewById(R.id.buttonRegister);

        /*
         * Listens to clicks on the {@code btnLogin }
         * When executed, it will invoke the {@code onClick}
         * method of {@code btnLogin}
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            /*
             * onClick Action for @code btnLogin
             * If the inputed userID and password entered for
             * @code username and @code password is valid, this will
             * bring the user to the main app page, or else it will
             * prompt a toast to the user of invalid login credentials
             */
            public void onClick(View view) {
                String un = username.getText().toString();
                String pwd = password.getText().toString();
                boolean valid = OrganizeMyLifeDB.loginverification(un,pwd);
                //First we check if any of the textfields are empty
                if(un.isEmpty() || pwd.isEmpty()){
                    if(un.isEmpty()){
                        username.setError("Username is empty");
                    }
                    if(pwd.isEmpty()){
                        password.setError("Password is empty");
                    }
                }
                //We then check if what was entered by the user is in the database
                else {
                    if (valid) {
                        Intent intent = new Intent(LoginPageActivity.this, MainPageActivity.class);
                        StaticUsername StUserName = new StaticUsername();
                        StaticUsername.username = un;
                        startActivity(intent);
                    } else {
                        StyleableToast.makeText(getApplicationContext(), "Incorrect Login", R.style.toastTheme).show();
                    }
                }
            }
        });
        //We segway our activity to the registration activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            /**
             *onClick Action for Register Button,
             * Upon user clicking the Register button it will bring the user
             * to the registration page.
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPageActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}
