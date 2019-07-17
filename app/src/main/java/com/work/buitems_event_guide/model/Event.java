package com.work.buitems_event_guide.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("cheif_guest")
    @Expose
    private String cheifGuest;
    @SerializedName("event_location")
    @Expose
    private String eventLocation;
    @SerializedName("event_date")
    @Expose
    private String eventDate;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("latitude")
    private String lat;

    @SerializedName("moderator")
    private String moderator;

    @SerializedName("speaker")
    private String speaker;

    @SerializedName("focal_person")
    private String focalPerson;

    @SerializedName("status")
    private String status;



    public String getModerator() {
        return moderator;
    }

    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getFocalPerson() {
        return focalPerson;
    }

    public void setFocalPerson(String focalPerson) {
        this.focalPerson = focalPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCheifGuest() {
        return cheifGuest;
    }

    public void setCheifGuest(String cheifGuest) {
        this.cheifGuest = cheifGuest;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

}
