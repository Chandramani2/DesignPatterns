package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;

public class Vehicle {

    DriveStrategy driveObj;

    //Constructor Injection:: We can add multiple Strategy without duplicating codes for same child classes functions
    Vehicle(DriveStrategy driveObj){
        this.driveObj = driveObj;
    }
    public void drive(){
        driveObj.drive();
    }

}
