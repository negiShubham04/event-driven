package com.shubham.syncEvent;

import java.time.Instant;

public abstract class Event {
    String id;
    Instant createdAt;
    String createdBy;
    EventType eventType;

    public abstract EventType getEventType();
}
