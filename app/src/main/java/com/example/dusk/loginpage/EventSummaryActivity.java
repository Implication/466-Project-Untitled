package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventSummaryActivity extends AppCompatActivity {
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);
        db = new DbHelper(this);
        String eventTitle = getIntent().getStringExtra("Title");
        String eventUN = getIntent().getStringExtra("un");
        String eventDesc = db.getEventInfo(eventUN, eventTitle);
        String eventMonth = getIntent().getStringExtra("Month");
        String eventDay = getIntent().getStringExtra("Day");
        String eventHour = getIntent().getStringExtra("Hour");
        String eventMin = getIntent().getStringExtra("Min");

        TextView title = findViewById(R.id.eventTitle);
        title.setText("Title: " + eventTitle);
        TextView desc = findViewById(R.id.eventDescription);
        desc.setText("Description: " + eventDesc);
        TextView month = findViewById(R.id.eventMonth);
        month.setText("Date: " + eventMonth);
        TextView day = findViewById(R.id.eventDay);
        day.setText(eventDay);
        TextView hour = findViewById(R.id.eventHour);
        hour.setText("Time: " + eventHour);
        TextView min = findViewById(R.id.eventMinute);
        min.setText(eventMin);

        Button returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventSummaryActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

    }

}
