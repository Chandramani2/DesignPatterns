package SingeltonPattern.Synchronized;

public class DBConnectionSync {

    private static DBConnectionSync dbConnectionSync;
    private DBConnectionSync(){

    }
    // if 1000's of threads comes it's too much to check for every thread and check if object is null or not
    synchronized public static DBConnectionSync getInstance(){
        if(dbConnectionSync == null){
            System.out.println("Singelton: SynchronizedInitialization Done");
            dbConnectionSync = new DBConnectionSync();
        }
        return dbConnectionSync;
    }
}
