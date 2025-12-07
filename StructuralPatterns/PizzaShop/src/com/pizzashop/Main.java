package com.pizzashop;

import com.pizzashop.base.Pizza;
import com.pizzashop.decorators.ExtraCheese;
import com.pizzashop.decorators.Mushroom;
import com.pizzashop.factory.PizzaFactory;

public class Main {
    public static void main(String[] args) {
        // 1. Factory creates the Base
        PizzaFactory factory = new PizzaFactory();
        Pizza myOrder = factory.createPizza("Margherita");
        System.out.println("Base: " + myOrder.getDescription() + " | $" + myOrder.getCost());

        // 2. Decorators add functionality (Toppings)
        myOrder = new ExtraCheese(myOrder);
        myOrder = new Mushroom(myOrder);

        System.out.println("Final: " + myOrder.getDescription() + " | $" + myOrder.getCost());
    }
}