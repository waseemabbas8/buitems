package com.work.buitems_event_guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.work.buitems_event_guide.api.BuitemsApi;
import com.work.buitems_event_guide.api.WebServices;
import com.work.buitems_event_guide.model.Event;
import com.work.buitems_event_guide.model.EventsResponse;
import com.work.buitems_event_guide.ui.EventsAdapter;
import com.work.buitems_event_guide.util.Helpers;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.work.buitems_event_guide.util.AppConstants.KEY_EVENT_OBJ;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private EditText search;
    private ListView eventsList;
    private ProgressBar mProgressBar;
    private Button adminLoginButton;

    EventsAdapter eventsAdapter;
    BuitemsApi apiService = WebServices.create();

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.et_search);
        mProgressBar = findViewById(R.id.progress_bar);
        eventsList = findViewById(R.id.events_list);
        adminLoginButton = findViewById(R.id.admin_login_button);

        initEventsList();

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Helpers.isUserLogin(MainActivity.this)){
                    Intent my_intent = new Intent(MainActivity.this,Admin_login.class);
                    startActivity(my_intent);
                }else {
                    Intent my_intent = new Intent(MainActivity.this,Event_Edit.class);
                    startActivity(my_intent);
                }
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
                if (MainActivity.this.eventsAdapter != null){
                    MainActivity.this.eventsAdapter.filter(search.getText().toString().toLowerCase(Locale.getDefault()));
                }
            }
        });

    }

    private void initEventsList() {
        Call eventsCall = apiService.getAllEvents();
        eventsCall.enqueue(new Callback<EventsResponse>(){

            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {

                if (response.isSuccessful()){
                    if(response.body() != null){
                        List<Event> events = response.body().getData();
                        eventsAdapter = new EventsAdapter(MainActivity.this, events);
                        eventsList.setAdapter(eventsAdapter);
                    }else {
                        Helpers.showInfoDialog(MainActivity.this, "Sorry Something Went Wrong");
                    }

                }else {
                    Helpers.showInfoDialog(MainActivity.this, "Sorry! some error occurred while fetching data from server.");
                }

                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {
                Helpers.showInfoDialog(MainActivity.this, t.getMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}