package com.shubham.syncEvent;

import java.time.Instant;

public class CreateOrderEvent extends Event {

    public CreateOrderEvent(String order, String customer) {
        this.id = order;
        this.createdBy = customer;
        this.createdAt = Instant.now();
    }

    @Override
    public EventType getEventType() {
        return EventType.CREATE_ORDER;
    }
}
