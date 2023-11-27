package StrategyPattern;

import StrategyPattern.Strategy.NormalStrategy;

public class NormalVehicle extends Vehicle{
    public NormalVehicle(){super (new NormalStrategy());}
}
