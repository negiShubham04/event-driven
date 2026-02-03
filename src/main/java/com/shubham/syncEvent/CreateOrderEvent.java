package com.shubham.syncEvent;

public class CreateOrderEvent implements Event {
    String orderId;
    String customerId;

    public CreateOrderEvent(String order, String customer) {
        orderId = order;
        customerId = customer;
    }

    public String getId() {
        return orderId;
    }
}
