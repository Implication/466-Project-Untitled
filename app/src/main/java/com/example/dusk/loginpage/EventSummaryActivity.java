package com.example.dusk.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Lisa on 4/29/2018.
 */

public class EventSummaryActivity extends AppCompatActivity {
    private DbHelper db;
    TextView title, descr, min, hour, month, day;
    Button returnMain = findViewById(R.id.returnButton);
    private static final String TAG= "EventSummaryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        db = new DbHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_summary);
        String username = getIntent().getStringExtra("un");
        String titleText = getIntent().getStringExtra("Title");
        title = findViewById(R.id.eventTitle);
        descr = findViewById(R.id.eventDescription);
        min = findViewById(R.id.eventMinute);
        hour = findViewById(R.id.eventHour);
        month = findViewById(R.id.eventMonth);
        day = findViewById(R.id.eventDay);

        title.setText("Title : " +getIntent().getStringExtra("Title"));
        descr.setText("Description : " + db.getEventInfo(username, titleText));
        min.setText("" +getIntent().getStringExtra("Min"));
        hour.setText("Time: " +getIntent().getStringExtra("Hour"));
        month.setText("Date: " + getIntent().getStringExtra("Month"));
        day.setText("" + getIntent().getStringExtra("Day"));

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventSummaryActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

    }


}
