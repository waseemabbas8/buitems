package com.work.buitems_event_guide;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.work.buitems_event_guide.model.Event;

import static com.work.buitems_event_guide.util.AppConstants.KEY_EVENT_OBJ;

public class mapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        event = (Event) getIntent().getSerializableExtra(KEY_EVENT_OBJ) ;

        SupportMapFragment mapFragment =(SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        if (!event.getLat().isEmpty()){
            LatLng buitems = new LatLng(Double.valueOf(event.getLat()), Double.valueOf(event.getLongitude()));
            map.addMarker(new MarkerOptions().position(buitems).title(event.getEventLocation()));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(buitems,30f));
            map.getMinZoomLevel();
        }

    }
}
