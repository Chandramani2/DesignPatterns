package Toppings.Impl;

import BasePizza.abstractClass.BasePizza;
import Toppings.abstractClass.ToppingDecorator;

public class MushRoom extends ToppingDecorator {

    BasePizza basePizza;

    public MushRoom(BasePizza basePizza){
        this.basePizza = basePizza;
    }
    @Override
    public int cost() {
        return this.basePizza.cost() + 50;
    }
}
