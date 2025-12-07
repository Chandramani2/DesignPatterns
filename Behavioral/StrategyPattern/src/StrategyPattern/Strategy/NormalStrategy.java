package StrategyPattern.Strategy;

public class NormalStrategy implements DriveStrategy{
    public void drive(){
        System.out.println("Normal Drive Capability");
    }
}
