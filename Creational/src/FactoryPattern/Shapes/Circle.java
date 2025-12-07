package FactoryPattern.Shapes;

import static java.lang.Math.PI;

public class Circle implements Shape{
    double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        System.out.print("Circle Area: ");
        return PI*radius*radius;
    }

    @Override
    public void setLength(double length) {

    }

    @Override
    public void setBreadth(double breadth) {

    }

    @Override
    public void setSide(double side) {

    }

    @Override
    public void setWidth(double width) {

    }

    @Override
    public void setHeight(double height) {

    }
}
