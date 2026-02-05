package com.shubham.asyncEvent;

import com.shubham.syncEvent.*;

import java.util.List;

public class CreateOrderEventBus extends EventBus {
    RetryManager retryManager;
    DeadLetterQueue deadLetterQueue;

    public CreateOrderEventBus() {
        retryManager = new RetryManager(5);
        listeners.put(EventType.CREATE_ORDER, List.of(new InventoryService(), new NotificationService(), new PaymentService()));
    }

    @Override
    public <T extends Event> void publish(T event) throws IllegalAccessException {
        if (listeners.isEmpty()) throw new IllegalAccessException("Listeners cannot be empty");
        List<EventListener<?>> eventListeners = listeners.get(event.getEventType());
        for (EventListener<?> eventListener : eventListeners) {
            EventListener<T> el = (EventListener<T>) eventListener;
            executorService.submit(() -> executeEvent(el, event));
        }
    }


    private <T extends Event> void executeEvent(EventListener<T> el, T event) {
        try {
            el.onEvent(event);
        } catch (Exception e) {
            retryManager.recordFailure(event);
            if (retryManager.shouldRetry(event)) {
                System.out.println("retrying event: " + event.getId() + " retry: " + retryManager.getRetryCount(event));
                executeEvent(el, event);
            } else {
                deadLetterQueue.add(event);
            }
        }
    }
}
