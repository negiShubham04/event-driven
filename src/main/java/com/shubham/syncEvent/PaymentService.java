package com.shubham.syncEvent;

public class PaymentService implements EventListener<CreateOrderEvent> {
    @Override
    public void onEvent(CreateOrderEvent event) {
        System.out.println("Going to process payment for "+ event.getId());
        sleep(2000);
        System.out.println("Processed payment for "+ event.getId());
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ignore) {
        }
    }
}
