package com.work.buitems_event_guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.work.buitems_event_guide.model.Event;
import com.work.buitems_event_guide.util.AppConstants;

import static com.work.buitems_event_guide.util.AppConstants.KEY_EVENT_OBJ;

public class Event_Info extends Activity {
    private static final String TAG = "Event_Info";

    private TextView eventName, chiefGuest, dateTime, location;
    private Button mapButton;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__info);
         event = (Event) getIntent().getSerializableExtra(KEY_EVENT_OBJ) ;

        intiView();

        //set Data
        eventName.setText(event.getEventName());
        chiefGuest.setText(event.getCheifGuest());
        dateTime.setText(event.getEventDate());
        location.setText(event.getEventLocation());

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_intent= new Intent(Event_Info.this,mapActivity.class);
                my_intent.putExtra(KEY_EVENT_OBJ, event);
                startActivity(my_intent);
            }
        });


    }

    private void intiView() {
        eventName = findViewById(R.id.event_name);
        chiefGuest = findViewById(R.id.chief_quest);
        dateTime = findViewById(R.id.date_time);
        mapButton = findViewById(R.id.go_to_map);
        location = findViewById(R.id.event_location);
    }
}
