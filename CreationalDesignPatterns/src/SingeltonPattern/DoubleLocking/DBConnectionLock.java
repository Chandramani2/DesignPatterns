package SingeltonPattern.DoubleLocking;


public class DBConnectionLock {
    private static DBConnectionLock dbConnectionLock;
    private DBConnectionLock(){

    }
    public static DBConnectionLock getInstance(){
        if(dbConnectionLock == null){
            synchronized (DBConnectionLock.class) {
                if (dbConnectionLock == null) {
                    System.out.println("Singelton: Double Locking Done");
                    dbConnectionLock = new DBConnectionLock();
                }
            }
        }
        return dbConnectionLock;
    }
}
