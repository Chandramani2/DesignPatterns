package strategy.locking;

public class RedisLockProvider implements LockProvider {
    @Override
    public boolean tryLock(String key, String userId, long ttlMs) {
        return false;
    }

    @Override
    public void unlock(String key) {

    }

    @Override
    public boolean isLockExpired(String key) {
        return false;
    }

    @Override
    public boolean isLockedBy(String key, String userId) {
        return false;
    }
}
