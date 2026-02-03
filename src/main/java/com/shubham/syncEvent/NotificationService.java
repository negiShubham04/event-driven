package com.shubham.syncEvent;

public class NotificationService implements EventListener<CreateOrderEvent> {
    @Override
    public void onEvent(CreateOrderEvent event) {
        System.out.println("Sending notification for " + event.getId());
        sleep(600);
        System.out.println("Notification sent for " + event.getId());
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ignore) {
        }
    }
}
