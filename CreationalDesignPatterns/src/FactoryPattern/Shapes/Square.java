package FactoryPattern.Shapes;

public class Square implements Shape {
    double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
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
        System.out.print("Square Area: ");
        return side*side;
    }

    @Override
    public void setLength(double length) {

    }

    @Override
    public void setBreadth(double breadth) {

    }
}
