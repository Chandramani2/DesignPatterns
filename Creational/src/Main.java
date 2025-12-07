import FactoryPattern.ShapeFactory;
import FactoryPattern.Shapes.Shape;
import PrototypePattern.Student;
import SingeltonPattern.DoubleLocking.DBConnectionLock;
import SingeltonPattern.EagerInitialization.DBConnectionEager;
import SingeltonPattern.LazyInitialization.DBConnectionLazy;
import SingeltonPattern.Synchronized.DBConnectionSync;

public class Main {
    public static void main(String[] args) {
        //ProtoType Pattern -> clone objects and it's properties
        Student student1 = new Student(1, "chandramani", 24);
        Student student2 = (Student)student1.clone();
        System.out.println("Prototype Pattern: ");
        System.out.println(student2.toString());

        //Singelton Pattern -> Object CCreated only Once, so make constructor private
        System.out.println("Singelton Pattern: ");
        DBConnectionEager dbConnectionEager = DBConnectionEager.getInstance();
        System.out.println("Singelton Pattern: lazyPattern");
        DBConnectionLazy dbConnectionLazy1 = DBConnectionLazy.getInstance();
        DBConnectionLazy dbConnectionLazy2 = DBConnectionLazy.getInstance();
        DBConnectionSync dbConnectionSync = DBConnectionSync.getInstance();
        DBConnectionLock dbConnectionLock = DBConnectionLock.getInstance();

        //Factory Pattern
        System.out.println("Factory Pattern: ");
        ShapeFactory shapeFactory1 = new ShapeFactory();
        Shape circleObj = shapeFactory1.getInstance("circle");
        circleObj.setRadius(2);
        System.out.println(circleObj.calculateArea());
        ShapeFactory shapeFactory2 = new ShapeFactory();
        Shape rectangleObj = shapeFactory2.getInstance("rectangle");
        rectangleObj.setLength(5);
        rectangleObj.setBreadth(2);
        System.out.println(rectangleObj.calculateArea());
    }
}