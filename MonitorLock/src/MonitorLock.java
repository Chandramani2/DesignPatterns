public class MonitorLock {
    public synchronized void task1(){
        System.out.println("Task1 Started: ");
        try {
            Thread.sleep(10000);
            System.out.println("Task1 Completed: ");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void task2(){
        System.out.println("Task2 Started: Before Thread 2 starts ");
        synchronized (this){
            System.out.println("Task2 Completed: After Thread2 Starts");
        }
    }

    public void task3(){
        System.out.println("Task3 Completed: ");
    }
}
