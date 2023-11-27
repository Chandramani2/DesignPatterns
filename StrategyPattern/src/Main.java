import StrategyPattern.NormalVehicle;
import StrategyPattern.SportsVehicle;
import StrategyPattern.Vehicle;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new NormalVehicle();
        vehicle.drive();
    }
}