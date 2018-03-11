package com.example.dusk.loginpage;

/**
 * Created by Jared Fipps on 2/18/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class EventList implements Serializable {

    private static final long serialVersionUID = 1L;

    public ArrayList<EventClass> eventList = new ArrayList<EventClass>();

    public void addToList(EventClass event) {
        eventList.add(event);
    }

    public int getSize() {
        return eventList.size();
    }

    public String getIndexTitle(int i) {
        return eventList.get(i).getEventTitle();
    }

    public String getIndexDesc(int i) {
        return eventList.get(i).getDesc();
    }

    public String getIndexHour(int i) {
        return eventList.get(i).getHour();
    }

    public String getIndexMin(int i) {
        return eventList.get(i).getMin();
    }
}
