package com.shubham.syncEvent;

public class InventoryService implements EventListener<CreateOrderEvent>{
    @Override
    public void onEvent(CreateOrderEvent event) {
        System.out.println("Updating inventory for " + event.getId());
        sleep(1600);
        System.out.println("Updated inventory for " + event.getId());
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ignore) {
        }
    }
}
