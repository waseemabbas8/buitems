package com.work.buitems_event_guide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.work.buitems_event_guide.api.BuitemsApi;
import com.work.buitems_event_guide.api.WebServices;
import com.work.buitems_event_guide.model.AddEventResponse;
import com.work.buitems_event_guide.model.Event;
import com.work.buitems_event_guide.model.MyPlace;
import com.work.buitems_event_guide.ui.DatePickerFragment;
import com.work.buitems_event_guide.util.Helpers;
import com.work.buitems_event_guide.util.SampleData;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.work.buitems_event_guide.util.AppConstants.KEY_EVENT_OBJ;

public class EditEventActivity extends AppCompatActivity {

    private static final String TAG = "EditEventActivity";

    private EditText chiefGuest, name, dateTime;
    private Spinner location;
    private Button submit;

    private List<MyPlace> locationsList = SampleData.getLocations();
    private MyPlace selectedLocation = locationsList.get(0);
    BuitemsApi apiService = WebServices.create();

    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        event = (Event) getIntent().getSerializableExtra(KEY_EVENT_OBJ);

        initViews();

        initSpinner();

        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setText("Please Wait...");
                submit.setEnabled(true);

                if (event == null){
                    saveEvent();
                }else {
                    updateEvent();
                }
            }

        });

    }

    private void resetButton(){
        submit.setText("Submit");
        submit.setEnabled(true);
    }

    public void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
        newFragment.setEditText(dateTime);
    }



    private void saveEvent(){
        String chief = chiefGuest.getText().toString();
        String eventName = name.getText().toString();
        String eventDateTime = dateTime.getText().toString();

        if (chief.isEmpty()){
            chiefGuest.setError("Please Enter this field");
            return;
        }

        if (eventName.isEmpty()){
            name.setError("Please Enter an Event Name");
            return;
        }

        if (eventDateTime.isEmpty()){
            dateTime.setError("Please Select a Date and Time");
            return;
        }

        RequestBody mChief = RequestBody.create(MediaType.parse("text/plain"), chief);
        RequestBody mEventName = RequestBody.create(MediaType.parse("text/plain"), eventName);
        RequestBody mEventDateTime = RequestBody.create(MediaType.parse("text/plain"), eventDateTime);
        RequestBody mSelctedLocation = RequestBody.create(MediaType.parse("text/plain"), selectedLocation.getTitle());
        RequestBody mLat = RequestBody.create(MediaType.parse("text/plain"), Double.toString(selectedLocation.getLatLng().latitude));
        RequestBody mLong = RequestBody.create(MediaType.parse("text/plain"), Double.toString(selectedLocation.getLatLng().longitude));

        Call call = apiService.addEvent(mEventName, mChief, mSelctedLocation, mEventDateTime, mLat, mLong);
        call.enqueue(new Callback<AddEventResponse>(){

            @Override
            public void onResponse(Call<AddEventResponse> call, Response<AddEventResponse> response) {
                resetButton();
                if (response.isSuccessful()){
                    if(response.body() != null){
                        Toast.makeText(EditEventActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }else {
                        Helpers.showInfoDialog(EditEventActivity.this, "Sorry! Something Went Wrong");
                    }

                }else {
                    Helpers.showInfoDialog(EditEventActivity.this, "Sorry! some error occurred while fetching data from server.");
                }
            }

            @Override
            public void onFailure(Call<AddEventResponse> call, Throwable t) {
                Helpers.showInfoDialog(EditEventActivity.this, t.getMessage());
                resetButton();
            }
        });


    }

    private void updateEvent(){
        String chief = chiefGuest.getText().toString();
        String eventName = name.getText().toString();
        String eventDateTime = dateTime.getText().toString();

        if (chief.isEmpty()){
            chiefGuest.setError("Please Enter this field");
            return;
        }

        if (eventName.isEmpty()){
            name.setError("Please Enter an Event Name");
            return;
        }

        if (eventDateTime.isEmpty()){
            dateTime.setError("Please Select a Date and Time");
            return;
        }

        RequestBody mChief = RequestBody.create(MediaType.parse("text/plain"), chief);
        RequestBody mEventName = RequestBody.create(MediaType.parse("text/plain"), eventName);
        RequestBody mEventDateTime = RequestBody.create(MediaType.parse("text/plain"), eventDateTime);
        RequestBody mSelctedLocation = RequestBody.create(MediaType.parse("text/plain"), selectedLocation.getTitle());
        RequestBody mLat = RequestBody.create(MediaType.parse("text/plain"), Double.toString(selectedLocation.getLatLng().latitude));
        RequestBody mLong = RequestBody.create(MediaType.parse("text/plain"), Double.toString(selectedLocation.getLatLng().longitude));
        RequestBody mId = RequestBody.create(MediaType.parse("text/plain"), event.getEventId());

        Call call = apiService.updateEvent(mId, mEventName, mChief, mSelctedLocation, mEventDateTime, mLat, mLong);
        call.enqueue(new Callback<AddEventResponse>(){

            @Override
            public void onResponse(Call<AddEventResponse> call, Response<AddEventResponse> response) {
                resetButton();
                if (response.isSuccessful()){
                    if(response.body() != null){
                        Toast.makeText(EditEventActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }else {
                        Helpers.showInfoDialog(EditEventActivity.this, "Sorry! Something Went Wrong");
                    }

                }else {
                    Helpers.showInfoDialog(EditEventActivity.this, "Sorry! some error occurred while fetching data from server.");
                }
            }

            @Override
            public void onFailure(Call<AddEventResponse> call, Throwable t) {
                Helpers.showInfoDialog(EditEventActivity.this, t.getMessage());
                Log.d("error2", t.getMessage());
                resetButton();
            }
        });
    }

    private void initSpinner() {
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, SampleData.getLocations());

        location.setAdapter(spinnerArrayAdapter);


        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = locationsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLocation = locationsList.get(0);
            }
        });
    }

    private void initViews() {
        chiefGuest = findViewById(R.id.chief_quest);
        name = findViewById(R.id.event_name);
        dateTime = findViewById(R.id.et_date);
        location = findViewById(R.id.sp_location);
        submit = findViewById(R.id.btn_submit);

        if (event != null){
            chiefGuest.setText(event.getCheifGuest());
            name.setText(event.getEventName());
            dateTime.setText(event.getEventDate());
        }

    }
}
