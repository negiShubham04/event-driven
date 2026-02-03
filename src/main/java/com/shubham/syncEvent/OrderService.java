package com.shubham.syncEvent;

public class OrderService {

    public static void main(String[] args) {
        CreateOrderEvent createOrderEvent = new CreateOrderEvent("order1", "customer1");

        EventBus eventBus = new EventBus();
        eventBus.register(CreateOrderEvent.class, new PaymentService());
        eventBus.register(CreateOrderEvent.class, new InventoryService());
        eventBus.register(CreateOrderEvent.class, new NotificationService());
        eventBus.publish(createOrderEvent);
    }
}
