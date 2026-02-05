package context;

import strategy.RateLimiterStrategy;

public class RateLimiterContext {

    private RateLimiterStrategy rateLimiterStrategy;

    public RateLimiterContext(RateLimiterStrategy rateLimiterStrategy) {
        this.rateLimiterStrategy = rateLimiterStrategy;
    }
    public void setStrategy(RateLimiterStrategy strategy) {
        this.rateLimiterStrategy = strategy;
    }

    public boolean execute(String userId){
        boolean allowed = rateLimiterStrategy.allowRequest();
        System.out.println("Request for user " + userId + " -> " + (allowed ? "ALLOWED" : "REJECTED"));
        return allowed;
    }
}
