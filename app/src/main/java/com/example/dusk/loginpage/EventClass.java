package com.example.dusk.loginpage;

/**
 * Created by Jared Fipps on 2/18/2018.
 */

import java.io.Serializable;

public class EventClass implements Serializable {

    private static final long serialVersionUID = 1L;

    public String hour;
    public String minute;
    public String eventTitle;
    public String desc;
    public String from;

    public EventClass () { }

    public EventClass (String h, String m, String e, String d, String f) {
        hour = h;
        minute = m;
        eventTitle = e;
        desc = d;
        from = f;
    }

    public void setHour(String h) {
        hour = h;
    }
    public void setMinute(String m) {
        minute = m;
    }
    public void setEventTitle(String e) {
        eventTitle = e;
    }
    public void setDesc(String d) {
        desc = d;
    }
    public void setFrom(String f) { from = f; }

    public String getEventTitle () { return eventTitle; }
    public String getDesc () { return desc; }
    public String getHour () { return hour; }
    public String getMin () { return minute; }
    public String getFrom () { return from; }


}

