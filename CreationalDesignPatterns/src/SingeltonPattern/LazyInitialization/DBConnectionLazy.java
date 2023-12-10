package SingeltonPattern.LazyInitialization;

public class DBConnectionLazy {
    private static DBConnectionLazy lazyInitialization;
    private DBConnectionLazy(){
        //made constructor private so that no object is created
    }
    public static DBConnectionLazy getInstance(){
        if(lazyInitialization == null){
            System.out.println("Singelton: lazyInitialization Done");
            lazyInitialization = new DBConnectionLazy();
        }
        else {
            System.out.println("Singelton: lazyInitialization cannot be performed, use already created object or error happening because of Multiple threads, use synchronized or double locking mechanism");
        }
        return lazyInitialization;
    }
}
