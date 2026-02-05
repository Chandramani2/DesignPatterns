package strategy;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogStrategy implements RateLimiterStrategy{
    private final int limit;
    private final long windowSizeInMs;
    private final Queue<Long> timestamps = new LinkedList<>();

    public SlidingWindowLogStrategy(int limit, long windowSizeInMs) {
        this.limit = limit;
        this.windowSizeInMs = windowSizeInMs;
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while(!timestamps.isEmpty() && now - timestamps.peek() > windowSizeInMs){
            timestamps.poll();
        }
        if(timestamps.size() < limit){
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
