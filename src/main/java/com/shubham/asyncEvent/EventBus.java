package com.shubham.asyncEvent;

import com.shubham.syncEvent.Event;
import com.shubham.syncEvent.EventListener;
import com.shubham.syncEvent.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class EventBus {
    Map<EventType, List<EventListener<?>>> listeners = new HashMap<>();
    ExecutorService executorService   = Executors.newFixedThreadPool(3);

    public abstract <T extends Event> void publish(T event) throws IllegalAccessException;

    public void shutDown() {
        executorService.shutdownNow();
    }
}
