import context.RateLimiterContext;
import strategy.RateLimiterStrategy;
import strategy.TokenBucketStrategy;

public static void main(String[] args) {

    RateLimiterContext rateLimiter = new RateLimiterContext(new TokenBucketStrategy(5,1));

    System.out.println("--- Testing Token Bucket (Burst of 5) ---");
    for(int i = 0; i < 7; i++) rateLimiter.execute("UserA");
}