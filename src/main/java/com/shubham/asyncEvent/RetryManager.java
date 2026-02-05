package com.shubham.asyncEvent;

import com.shubham.syncEvent.Event;

import java.util.HashMap;
import java.util.Map;

public class RetryManager {
    int maxRetries;

    public RetryManager(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    Map<String, Integer> retryCount = new HashMap<>();

    public <T extends Event> void recordFailure(T event) {
        retryCount.merge(event.getId(), 1, Integer::sum);
    }

    public <T extends Event> boolean shouldRetry(T event) {
        return retryCount.getOrDefault(event.getId(), 0) <= maxRetries;
    }

    public <T extends Event> String getRetryCount(T event) {
        return String.valueOf(retryCount.getOrDefault(event.getId(), 0));
    }
}
