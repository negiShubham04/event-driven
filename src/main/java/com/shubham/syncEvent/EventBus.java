package com.shubham.syncEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();

    public <T extends Event> void register(Class<T> event, EventListener<T> eventListener) {
        listeners.computeIfAbsent(event, x -> new ArrayList<>()).add(eventListener);
    }

    public <T extends Event> void publish(T event) throws Exception {
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        for(EventListener<?> eventListener: eventListeners){
            EventListener<T> el = (EventListener<T>)eventListener;
            el.onEvent(event);
        }
    }
}
