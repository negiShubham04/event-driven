package com.shubham.asyncEvent;

import com.shubham.syncEvent.Event;
import com.shubham.syncEvent.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class EventBus {
    private Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();
    ExecutorService executorService   = Executors.newFixedThreadPool(3);

    public <T extends Event> void register(Class<T> event, EventListener<T> eventListener) {
        listeners.computeIfAbsent(event, x -> new ArrayList<>()).add(eventListener);
    }

    public <T extends Event> void publish(T event) {
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        for(EventListener<?> eventListener: eventListeners){
            EventListener<T> el = (EventListener<T>)eventListener;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    el.onEvent(event);
                }
            });
        }
    }

    public void shutDown() {
        executorService.shutdownNow();
    }
}
