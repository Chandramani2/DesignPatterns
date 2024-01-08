package Toppings.Impl;

import BasePizza.abstractClass.BasePizza;
import Toppings.abstractClass.ToppingDecorator;

public class ExtraCheese extends ToppingDecorator {
    BasePizza basePizza;
    public ExtraCheese(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 20;
    }
}
