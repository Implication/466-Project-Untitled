package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;


public class addEventActivity extends AppCompatActivity {

    public EventClass event = new EventClass();
    public ArrayList<EventClass> eventList = new ArrayList<EventClass>();

    public String checkFlag;
    private String un;
    private String pwd;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Intent intent = getIntent();
        eventList = (ArrayList<EventClass>) intent.getSerializableExtra("events");
        un = intent.getStringExtra("Username");
    }

    public void addEvent (View v) {
        db = new DbHelper(this);
        EditText title = findViewById(R.id.title);
        String t = title.getText().toString();

        EditText desc = findViewById(R.id.description);
        String d = desc.getText().toString();

        EditText hourText = findViewById(R.id.hour);
        String hourString = hourText.getText().toString();

        EditText minText = findViewById(R.id.minute);
        String minString = minText.getText().toString();

        EditText monthText = findViewById(R.id.month);
        String monthString = monthText.getText().toString();

        EditText dayText = findViewById(R.id.day);
        String dayString = dayText.getText().toString();
        if(t.isEmpty() || hourString.isEmpty() || minString.isEmpty() || Integer.parseInt(hourString) < 0 || Integer.parseInt(hourString) > 24
                || Integer.parseInt(minString) < 0 || Integer.parseInt(minString) > 60 || Integer.parseInt(monthString) < 1 || Integer.parseInt(monthString) > 12
                || Integer.parseInt(dayString) < 1 || Integer.parseInt(dayString) > 31){
            if(t.isEmpty()){
                title.setError("Please enter a title");
            }
            if(hourString.isEmpty()){
                hourText.setError("Please enter the hour string");
            }
            if(minString.isEmpty()){
                minText.setError("Please enter the minutes");
            }
            if (Integer.parseInt(hourString) < 0 || Integer.parseInt(hourString) > 24) {
                hourText.setError("Hour needs to be between 0 and 24");
            }
            if (Integer.parseInt(minString) < 0 || Integer.parseInt(minString) > 60) {
                minText.setError("Minute needs to be between 0 and 60");
            }
            if (Integer.parseInt(monthString) < 0 || Integer.parseInt(monthString) > 12) {
                monthText.setError("Month needs to be between 0 and 12");
            }
            if (Integer.parseInt(dayString) < 0 || Integer.parseInt(dayString) > 31) {
                dayText.setError("Day needs to be between 0 and 31");
            }

        }
        else {
            db.addTask(un, t, d, hourString, minString, monthString, dayString);
            checkFlag = "addEvent";

            StyleableToast.makeText(this, "Event Added", R.style.toastTheme).show();

            Intent intent = new Intent(this, MainPageActivity.class);
            intent.putExtra("flag", checkFlag);
            intent.putExtra("eventUpdate", eventList);
            intent.putExtra("Username", un);
            startActivity(intent);
        }
    }

}


