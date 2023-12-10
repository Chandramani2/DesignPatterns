package FactoryPattern;

import FactoryPattern.Shapes.*;

public class ShapeFactory {

    public Shape getInstance(String str){
        if(str.equals("circle")){
            return new Circle();
        }
        else if(str.equals("rectangle")){
            return new Rectangle();
        }
        else if(str.equals("square")){
            return new Square();
        }
        else if(str.equals("triangle")){
            return new Triangle();
        }
        return null;
    }
}
