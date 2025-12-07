package FactoryPattern.Shapes;

public class Rectangle implements Shape{
    double length;
    double breadth;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
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

    @Override
    public void setRadius(double radius) {

    }

    @Override
    public double calculateArea() {
        System.out.print("Rectangle Area: ");
        return length*breadth;
    }
}
