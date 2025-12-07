package FactoryPattern.Shapes;

public class Triangle implements Shape{
    double width;
    double height;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void setRadius(double radius) {

    }

    @Override
    public double calculateArea() {
        System.out.print("Triangle Area: ");
        return (1/2)*width*height;
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
}
