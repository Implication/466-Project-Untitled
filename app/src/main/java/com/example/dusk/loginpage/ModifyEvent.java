package com.example.dusk.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class ModifyEvent extends AppCompatActivity {
    String oldTitle;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_event);
        db = new DbHelper(this);
        Intent intent = getIntent();

        //Get info from selected card (title, hour, minute, description
        String title = intent.getStringExtra("title");
        oldTitle = title;
        Log.i("Title", oldTitle);
        String hour = intent.getStringExtra("hour");
        String minute = intent.getStringExtra("minute");
        String username = intent.getStringExtra("username");
        String desc = db.getEventInfo(username, title);
        Log.i("DESC", desc);

        //Set edit text views to the current card info
        EditText titleText = findViewById(R.id.titleMod);
        titleText.setText(title);
        EditText descText = findViewById(R.id.descriptionMod);
        descText.setText(desc);
        EditText hourText = findViewById(R.id.hourMod);
        hourText.setText(hour);
        EditText minuteText = findViewById(R.id.minuteMod);
        minuteText.setText(minute);
        Button modButton = findViewById(R.id.modButton);
        db = new DbHelper(this);
        modButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){

            EditText titleSend = findViewById(R.id.titleMod);
            String titleMod = titleSend.getText().toString();
            EditText descSend = findViewById(R.id.descriptionMod);
            String editMod = descSend.getText().toString();
            EditText hourSend = findViewById(R.id.hourMod);
            String hourMod = hourSend.getText().toString();
            EditText minuteSend = findViewById(R.id.minuteMod);
            String minuteMod = minuteSend.getText().toString();

            db.modifyEvent(oldTitle, titleMod, editMod, hourMod, minuteMod);
            Intent intent = new Intent(ModifyEvent.this,MainPageActivity.class);
            startActivity(intent);
        }
    });
    }
}
