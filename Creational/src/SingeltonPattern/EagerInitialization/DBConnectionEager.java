package SingeltonPattern.EagerInitialization;

public class DBConnectionEager {

    private static DBConnectionEager eagerInitialization = new DBConnectionEager();
    private DBConnectionEager(){
        //made constructor private so that no object is created
    }

    public static DBConnectionEager getInstance(){
        System.out.println("Singelton: EagerInitialization Done");
        return eagerInitialization;
    }
}
