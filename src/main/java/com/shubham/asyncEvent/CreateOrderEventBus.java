package com.shubham.asyncEvent;

import com.shubham.syncEvent.*;

import java.util.List;

public class CreateOrderEventBus extends EventBus {
    public CreateOrderEventBus() {
        listeners.put(EventType.CREATE_ORDER, List.of(new InventoryService(), new NotificationService(), new PaymentService()));
    }

    @Override
    public <T extends Event> void publish(T event) throws IllegalAccessException {
        if(listeners.isEmpty())throw new IllegalAccessException("Listeners cannot be empty");
        List<EventListener<?>> eventListeners = listeners.get(event.getEventType());
        for (EventListener<?> eventListener : eventListeners) {
            EventListener<T> el = (EventListener<T>) eventListener;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    el.onEvent(event);
                }
            });
        }
        executorService.shutdownNow();
    }
}
