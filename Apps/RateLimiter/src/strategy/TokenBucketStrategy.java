package strategy;

public class TokenBucketStrategy implements RateLimiterStrategy {
    private final long capacity;
    private final long refillRate;
    private long lastRefillTimestamp;
    private long currentTokens;

    public TokenBucketStrategy(long capacity, long refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.currentTokens = capacity;
    }

    @Override
    public boolean allowRequest() {
        refill();
        if(currentTokens > 0) {
            currentTokens--;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long tokensToAdd = (now - lastRefillTimestamp) * refillRate / 1000;
        currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
        lastRefillTimestamp = now;
    }
}
