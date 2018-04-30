package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsActivity extends AppCompatActivity {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\\Q\\?[]{}.\\E#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);
    Button btnCancel;
    Button btnSave;
    DbHelper OrganizeMyLifeDB;
    private String username;
    private EditText fullname;
    private EditText password;
    private EditText newpassword;
    private EditText email;
    private ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        OrganizeMyLifeDB = new DbHelper(this);


        Bundle intent = getIntent().getExtras();
        StaticUsername un = new StaticUsername();
        username = StaticUsername.username;
        fullname = findViewById(R.id.nameText);
        password = findViewById(R.id.passwordText);
        newpassword = findViewById(R.id.newPasswordText);
        email = findViewById(R.id.emailText);

        al = OrganizeMyLifeDB.SettingsValues(username);

        fullname.setText(al.get(0));
        email.setText(al.get(1));

        btnCancel = findViewById(R.id.cancelButton);
        btnSave = findViewById(R.id.saveButton);

        //We want to cancel any settings changes
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
        //We want to save the changes we made on the settings page
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                String newpwd = newpassword.getText().toString();
                String fn = fullname.getText().toString();
                String pwd = password.getText().toString();
                Matcher e = VALID_EMAIL_ADDRESS_REGEX.matcher(email.getText().toString());
                Matcher p = VALID_PASSWORD_REGEX.matcher(pwd);
                Matcher np = VALID_PASSWORD_REGEX.matcher(newpwd);
                String el = Boolean.toString(e.find());
                Log.d("duo", el);
                //We check if any of the fields are empty
                if (fn.isEmpty() || el != "true" || !p.find() || !np.find()) {
                    if(fullname.getText().toString().isEmpty()){
                        fullname.setError("Name is empty");
                    }
                    if (!np.find()) {
                        newpassword.setError("Password must have 1 uppercase and lowercase letter, a number, and a special character");
                    }
                    if (el != "true") {
                        email.setError("Enter a valid email address");
                    }

                    if (!p.find()) {
                        password.setError("Password must have 1 uppercase and lowercase letter, a number, and a special character");
                    }


                }
                //We also check if teh password matches the current password
                else if(!OrganizeMyLifeDB.loginverification(username,pwd)){
                    password.setError("Current password does not match");
                    StyleableToast.makeText(getApplicationContext(), "Current password does not match", R.style.toastTheme).show();
                }
                //After all checks we place it back into our database
                else{
                    Intent intent = new Intent(SettingsActivity.this, MainPageActivity.class);
                    StyleableToast.makeText(getApplicationContext(), "Settings Saved", R.style.toastTheme).show();
                    OrganizeMyLifeDB.updateDatabase(username, email.getText().toString(), newpassword.getText().toString(), fullname.getText().toString());
                    startActivity(intent);
             }
            }
        });
    }
}
