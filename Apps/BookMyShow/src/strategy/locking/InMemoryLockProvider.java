package strategy.locking;

import java.util.concurrent.*;

public class InMemoryLockProvider implements LockProvider {

    public static class Expiry{
        final long deadline;
        final String owner;
        public Expiry(long deadline, String owner) {
            this.deadline = deadline;
            this.owner = owner;
        }
    }

    private final ConcurrentMap<String, Expiry> locks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public InMemoryLockProvider() {
        scheduler.scheduleAtFixedRate(this::sweep, 1, 1, TimeUnit.SECONDS);
    }

    private void sweep() {
        long now = System.currentTimeMillis();
        locks.entrySet().removeIf(entry -> entry.getValue().deadline <= now);
    }

    @Override
    public boolean tryLock(String key, String userId, long ttlMs) {
        long now = System.currentTimeMillis();
        Expiry expiry = new Expiry(now + ttlMs, userId);
        return locks.compute(key, (k,v) -> (v==null || v.deadline <= now) ? expiry : v) == expiry;
    }

    @Override
    public void unlock(String key) {
        locks.remove(key);
    }

    @Override
    public boolean isLockExpired(String key) {
        Expiry e = locks.get(key);
        return e!=null && e.deadline < System.currentTimeMillis();
    }

    @Override
    public boolean isLockedBy(String key, String userId) {
        Expiry e = locks.get(key);
        return e!=null && e.owner.equals(userId);
    }
}
