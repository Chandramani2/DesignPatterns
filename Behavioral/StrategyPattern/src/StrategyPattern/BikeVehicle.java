package StrategyPattern;

import StrategyPattern.Strategy.BikeStrategy;

public class BikeVehicle extends Vehicle{
    public BikeVehicle(){super (new BikeStrategy());}
}
