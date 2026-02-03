package com.shubham.syncEvent;

public interface EventListener<T extends Event> {
    void onEvent(T event);
}
