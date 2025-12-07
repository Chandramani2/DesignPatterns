package StrategyPattern;

import StrategyPattern.Strategy.DriveStrategy;
import StrategyPattern.Strategy.SpecialStrategy;

public class SportsVehicle extends Vehicle{
    public SportsVehicle() { super (new SpecialStrategy());}
}
