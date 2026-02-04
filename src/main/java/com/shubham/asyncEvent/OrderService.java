package com.shubham.asyncEvent;

import com.shubham.syncEvent.*;

public class OrderService {

    public static void main(String[] args) throws IllegalAccessException {
        CreateOrderEvent createOrderEvent = new CreateOrderEvent("order1", "customer1");

        CreateOrderEventBus eventBus = new CreateOrderEventBus();
        eventBus.publish(createOrderEvent);
    }
}
