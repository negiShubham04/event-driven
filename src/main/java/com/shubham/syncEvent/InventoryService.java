package com.shubham.syncEvent;

import java.io.IOException;
import java.util.Random;

public class InventoryService implements EventListener<CreateOrderEvent> {
    @Override
    public void onEvent(CreateOrderEvent event) throws Exception {
        System.out.println("Updating inventory for " + event.getId());
        handle();
        System.out.println("Updated inventory for " + event.getId());
    }

    private void handle() throws IOException {
        int v = new Random().nextInt(6);
        if (v != 0 && v != 1  && v != 2)
            throw new IOException("testing error");
    }
}
