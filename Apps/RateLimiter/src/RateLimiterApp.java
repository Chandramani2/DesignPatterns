import context.RateLimiterContext;
import strategy.FixedWindowStrategy;
import strategy.RateLimiterStrategy;
import strategy.TokenBucketStrategy;

public static void main(String[] args) {

    RateLimiterContext rateLimiter = new RateLimiterContext(new TokenBucketStrategy(5,1));

    System.out.println("--- Testing Token Bucket (Burst of 5) ---");
    for(int i = 0; i < 7; i++) rateLimiter.execute("UserA");

    // 2. Switch to Fixed Window dynamically
    System.out.println("\n--- Switching to Fixed Window (Limit 2 per sec) ---");
    rateLimiter.setStrategy(new FixedWindowStrategy(2, 1000));
    rateLimiter.execute("UserB");
    rateLimiter.execute("UserB");
    rateLimiter.execute("UserB"); // Should fail
}