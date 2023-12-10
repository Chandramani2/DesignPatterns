public class Main {
    public static void main(String[] args) {
            MonitorLock obj = new MonitorLock();
            //    Thread t1 = new Thread(() -> obj.task1());
            MonitorThread runnableObj = new MonitorThread(obj);
            Thread t1 = new Thread(runnableObj);
            Thread t2 = new Thread(() -> obj.task2());
            Thread t3 = new Thread(() -> obj.task3());

            t1.start();
            t2.start();
            t3.start();
    }
}