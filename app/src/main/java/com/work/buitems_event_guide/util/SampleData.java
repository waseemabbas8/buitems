package com.work.buitems_event_guide.util;

import com.google.android.gms.maps.model.LatLng;
import com.work.buitems_event_guide.model.MyPlace;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

    public static List<MyPlace> getLocations() {
        ArrayList<MyPlace> pickUps = new ArrayList<>();

        pickUps.add(new MyPlace("Arfa Karim Randhava Expo Center Buitems", new LatLng(30.2664424, 66.9435221)));
        pickUps.add(new MyPlace("BUITEMS New Block", new LatLng(30.2664424, 66.9435221)));
        pickUps.add(new MyPlace("Markhor Auditorium, BUITEMS", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("BUITEMS Sports Complex", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("Boys hostel block no 2 BUITEMS", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("Jamia Masjid", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("BUITEMS, Visiting Faculty Guest House", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("Fatima Jinnah Hostel for girls", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("Faculty Hostel", new LatLng(30.2704723, 66.9380011)));
        pickUps.add(new MyPlace("Habib Bank", new LatLng(30.2706645, 66.9370788)));
        pickUps.add(new MyPlace("BUITEMS Cafeteria", new LatLng(30.2706645,66.9370788)));
        pickUps.add(new MyPlace("MAIN CAFETERIA, BUITEMS", new LatLng(30.2706645,66.9370788)));
        pickUps.add(new MyPlace("Owais Ghani Hall", new LatLng(30.268887, 66.944269)));

        return pickUps;
    }

}
