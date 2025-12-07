package StrategyPattern.Strategy;

public class BikeStrategy implements DriveStrategy{
    public void drive(){
        System.out.println("Bike Drive Capability");
    }
}
