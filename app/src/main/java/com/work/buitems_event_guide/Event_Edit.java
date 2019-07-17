package com.work.buitems_event_guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.work.buitems_event_guide.api.BuitemsApi;
import com.work.buitems_event_guide.api.WebServices;
import com.work.buitems_event_guide.model.Event;
import com.work.buitems_event_guide.model.EventsResponse;
import com.work.buitems_event_guide.ui.EventsAdapter;
import com.work.buitems_event_guide.util.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.work.buitems_event_guide.util.AppConstants.KEY_USER_LOGIN;

public class Event_Edit extends Activity {
    private static final String TAG = "MainActivity";

    private EditText search;
    private ListView eventsList;
    private ProgressBar mProgressBar;
    private Button logout, addEvent;

    EventsAdapter eventsAdapter;
    BuitemsApi apiService = WebServices.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__edit);

        initViews();

        initEventsList();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
                Intent my_intent = new Intent(Event_Edit.this,Admin_login.class);
                startActivity(my_intent);
                finish();
            }
        });

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my_intent = new Intent(Event_Edit.this, EditEventActivity.class);
                startActivity(my_intent);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Event_Edit.this.eventsAdapter != null){
                    Event_Edit.this.eventsAdapter.filter(search.getText().toString().toLowerCase(Locale.getDefault()));
                }
            }
        });


    }

    private void logoutUser(){
        SharedPreferences.Editor editor = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE).edit();
        editor.remove(KEY_USER_LOGIN).apply();
    }

    private void initViews() {
        search = findViewById(R.id.et_search);
        mProgressBar = findViewById(R.id.progress_bar);
        eventsList = findViewById(R.id.events_list);
        logout = findViewById(R.id.btn_logout);
        addEvent = findViewById(R.id.btn_add_event);
    }

    private void initEventsList() {
        Call eventsCall = apiService.getAllEvents();
        eventsCall.enqueue(new Callback<EventsResponse>() {

            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Event> events = response.body().getData();
                        eventsAdapter = new EventsAdapter(Event_Edit.this, events);
                        eventsList.setAdapter(eventsAdapter);
                    } else {
                        Helpers.showInfoDialog(Event_Edit.this, response.body().getMessage());
                    }

                } else {
                    Helpers.showInfoDialog(Event_Edit.this, "Sorry! some error occurred while fetching data from server.");
                }

                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                Helpers.showInfoDialog(Event_Edit.this, t.getMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        });

    }

}
