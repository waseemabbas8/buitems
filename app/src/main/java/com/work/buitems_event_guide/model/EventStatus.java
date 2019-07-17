package com.work.buitems_event_guide.model;

import java.util.HashMap;
import java.util.Map;

public enum EventStatus {
    OnTime(1),
    Delayed(2),
    Canceled(3),
    Passed(4);

    private int value;
    private static Map map = new HashMap<>();

    EventStatus(int value){
        this.value = value;
    }

    static {
        for (EventStatus eventStatus : EventStatus.values()) {
            map.put(eventStatus.value, eventStatus);
        }
    }

    public int getValue() {
        return value;
    }

    public static EventStatus valueOf(int value){
        return (EventStatus) map.get(value);
    }

    @Override public String toString(){

        if (value == 1) return "On Time";

        if (value == 2) return "Delayed";

        if (value == 3) return "Canceled";

        if (value == 4) return "Passed";

        return "Status";
    }

}
