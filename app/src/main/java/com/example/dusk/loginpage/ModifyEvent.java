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
        String month = intent.getStringExtra("month");
        String day = intent.getStringExtra("day");
        String desc = db.getEventInfo(username, title);
        //Set edit text views to the current card info
        final EditText titleText = findViewById(R.id.titleMod);
        titleText.setText(title);
        EditText descText = findViewById(R.id.descriptionMod);
        descText.setText(desc);
        EditText hourText = findViewById(R.id.hourMod);
        hourText.setText(hour);
        EditText minuteText = findViewById(R.id.minuteMod);
        minuteText.setText(minute);
        EditText monthText = findViewById(R.id.monthMod);
        monthText.setText(month);
        EditText dayText = findViewById(R.id.minuteMod);
        dayText.setText(day);
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
            EditText monthSend = findViewById(R.id.monthMod);
            String monthMod = monthSend.getText().toString();
            EditText daySend = findViewById(R.id.dayMod);
            String dayMod = daySend.getText().toString();

            if(titleMod.isEmpty() || hourMod.isEmpty() || minuteMod.isEmpty() || Integer.parseInt(hourMod) < 0 || Integer.parseInt(hourMod) > 24
                    || Integer.parseInt(minuteMod) < 0 || Integer.parseInt(minuteMod) > 60 || Integer.parseInt(monthMod) < 1 || Integer.parseInt(monthMod) > 12
                    || Integer.parseInt(dayMod) < 1 || Integer.parseInt(dayMod) > 31){
                if(titleMod.isEmpty()){
                    titleSend.setError("Please enter a title");
                }
                if(hourMod.isEmpty()){
                    hourSend.setError("Please enter the hour string");
                }
                if(minuteMod.isEmpty()){
                    minuteSend.setError("Please enter the minutes");
                }
                if (Integer.parseInt(hourMod) < 0 || Integer.parseInt(hourMod) > 24) {
                    hourSend.setError("Hour needs to be between 0 and 24");
                }
                if (Integer.parseInt(minuteMod) < 0 || Integer.parseInt(minuteMod) > 60) {
                    minuteSend.setError("Minute needs to be between 0 and 60");
                }
                if (Integer.parseInt(monthMod) < 0 || Integer.parseInt(monthMod) > 12) {
                    monthSend.setError("Month needs to be between 0 and 12");
                }
                if (Integer.parseInt(dayMod) < 0 || Integer.parseInt(dayMod) > 31) {
                    daySend.setError("Day needs to be between 0 and 31");
                }

            }
            else {
                db.modifyEvent(oldTitle, titleMod, editMod, hourMod, minuteMod, monthMod, dayMod);
                Intent intent = new Intent(ModifyEvent.this,MainPageActivity.class);
                startActivity(intent);
            }
        }
    });
    }
}
