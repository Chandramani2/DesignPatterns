import BasePizza.Impl.FarmHouse;
import BasePizza.Impl.Margharita;
import BasePizza.Impl.VegDelight;
import BasePizza.abstractClass.BasePizza;
import Toppings.Impl.ExtraCheese;
import Toppings.Impl.MushRoom;

public class Main {
    public static void main(String[] args) {
        BasePizza basePizza = new ExtraCheese(new Margharita());
        System.out.println("Your Pizza ( Margharita + ExtraCheese ) Costs: " + basePizza.cost());
        basePizza = new MushRoom(new Margharita());
        System.out.println("Your Pizza ( Margharita + MushRoom ) Costs: " + basePizza.cost());
        basePizza = new ExtraCheese(new FarmHouse());
        System.out.println("Your Pizza ( FarmHouse + ExtraCheese ) Costs: " + basePizza.cost());
        basePizza = new MushRoom(new FarmHouse());
        System.out.println("Your Pizza ( FarmHouse + MushRoom ) Costs: " + basePizza.cost());
        basePizza = new MushRoom(new ExtraCheese(new FarmHouse()));
        System.out.println("Your Pizza ( FarmHouse + ExtraCheese + MushRoom) Costs: " + basePizza.cost());
    }
}