package com.shubham.asyncEvent;

import com.shubham.syncEvent.Event;

import java.util.ArrayList;
import java.util.List;

public class DeadLetterQueue {
    List<Event> eventList = new ArrayList<>();

    public <T extends Event> void add(T event) {
        eventList.add(event);
    }
}
