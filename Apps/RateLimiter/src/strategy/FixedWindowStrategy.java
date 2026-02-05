package strategy;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowStrategy implements RateLimiterStrategy {

    private final int limit;
    private final long windowSizeInMs;
    private long windowStart;
    private final AtomicInteger counter = new AtomicInteger(0);

    public FixedWindowStrategy(int limit, long windowSizeInMs) {
        this.windowStart = System.currentTimeMillis();
        this.windowSizeInMs = windowSizeInMs;
        this.limit = limit;
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if(now - windowStart > windowSizeInMs) {
            windowStart = now;
            counter.set(0);
        }
        return counter.incrementAndGet() <= limit;
    }
}
