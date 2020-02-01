package com.android.attendance.bean;

public class EventBean {
    private int event_id;
    private String event_date;
    private String event_venues;
    private String event_Timing;
    private String event_name;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_venues() {
        return event_venues;
    }

    public void setEvent_venues(String event_venues) {
        this.event_venues = event_venues;
    }

    public String getEvent_Timing() {
        return event_Timing;
    }

    public void setEvent_Timing(String event_Timing) {
        this.event_Timing = event_Timing;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }
}
