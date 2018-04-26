package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dusk.loginpage.GMailSender;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText username;
    //TextView forgottenPassword;
    //Button checkID;
    Button send;
    Button back;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        db = new DbHelper(this);
        username = findViewById(R.id.forgotUserId);
        //forgottenPassword = findViewById(R.id.forgotPasswordButton);
        //checkID = findViewById(R.id.CheckIdButton);
        back = findViewById(R.id.backButton);
        send = findViewById(R.id.forgotPassword);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = db.forgotPasswordSearch(username.getText().toString());
                final String email = db.getUserEmail(username.getText().toString());
                if (password != null) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                 GMailSender sender = new GMailSender(
                                            "noreplyremindme@gmail.com",
                                            "Remind!23");
                                    sender.sendMail("Remind Me Password", "Your password is: " + password,
                                            "noreplyreemindme@gmail.com",
                                            email);
                            } catch (Exception e) {
                                StyleableToast.makeText(getApplicationContext(), "Error", R.style.toastTheme).show();
                            }
                        }
                    }).start();
                    StyleableToast.makeText(getApplicationContext(), "Email sent", R.style.toastTheme).show();
                }
                else {
                    StyleableToast.makeText(getApplicationContext(), "Account not found", R.style.toastTheme).show();
                }
            }
        });


        /*checkID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String password = db.forgotPasswordSearch(username.getText().toString());
                String email = db.getUserEmail(username.getText().toString());
                if(password != null){
                    /*Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Remind Me Password");
                    intent.putExtra(Intent.EXTRA_TEXT, "Your password is: " + password);

                    intent.setType("message/rfc882");

                    startActivity(Intent.createChooser(intent, "Select email app"));*/

                    /*GMailSender sender = new GMailSender("noreplyremindme@gmail.com", "Remind!23");
                    sender.sendMail("Remind Me Password",
                            "This is your password: " + password,
                            "noreplyremindme@gmail.com",
                            email);

                    StyleableToast.makeText(getApplicationContext(), "Emailed password", R.style.toastTheme).show();
                }
                else{
                    StyleableToast.makeText(getApplicationContext(), "Account does not exist.", R.style.toastTheme).show();;
                }
            }
        });*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });
    }


}
