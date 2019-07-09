package com.work.buitems_event_guide.model;

import com.google.android.gms.maps.model.LatLng;

public class MyPlace {
    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    String title;  //the title wil be used to display on map marker
    LatLng latLng; //this will point the marker to a specific location


    public MyPlace(String title, LatLng latLng) {
        this.title = title;
        this.latLng = latLng;
    }



}
