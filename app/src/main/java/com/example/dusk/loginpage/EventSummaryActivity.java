package com.example.dusk.loginpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Lisa on 4/29/2018.
 */

public class EventSummaryActivity extends AppCompatActivity {

    TextView title, descr, min, hour;
    private static final String TAG= "EventSummaryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);
        title = findViewById(R.id.eventTitle);
        descr = findViewById(R.id.eventDescription);
        min = findViewById(R.id.eventMinute);
        hour = findViewById(R.id.eventHour);

        title.setText("Title : " +getIntent().getStringExtra("title"));
        //descr.setText("Description : " +getIntent().getStringExtra("description"));
        min.setText("" +getIntent().getStringExtra("min"));
        hour.setText("Time: " +getIntent().getStringExtra("hour"));

    }


}
