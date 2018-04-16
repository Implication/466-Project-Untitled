package com.example.dusk.loginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText username;
    TextView forgottenPassword;
    Button checkID;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        db = new DbHelper(this);
        username = findViewById(R.id.forgotUserId);
        forgottenPassword = findViewById(R.id.passwordRecoveryText);
        checkID = findViewById(R.id.CheckIdButton);
        checkID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String password = db.forgotPasswordSearch(username.getText().toString());
                if(password != null){
                    forgottenPassword.setText(password);
                    forgottenPassword.setVisibility(View.VISIBLE);
                }
                else{
                    Toast.makeText(getApplicationContext(),"ID Dosent Exist", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
