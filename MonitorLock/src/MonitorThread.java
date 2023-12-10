public class MonitorThread implements Runnable {
    MonitorLock obj;

    public MonitorThread(MonitorLock obj) {
        this.obj = obj;
    }
    @Override
    public void run() {
        obj.task1();
    }
}
