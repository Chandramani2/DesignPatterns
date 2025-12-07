package com.pizzashop.factory;

import com.pizzashop.base.Pizza;
import com.pizzashop.products.Margherita;
import com.pizzashop.products.Farmhouse;

public class PizzaFactory {

    public Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("Margherita")) {
            return new Margherita();
        } else if (type.equalsIgnoreCase("Farmhouse")) {
            return new Farmhouse();
        } else {
            throw new IllegalArgumentException("Unknown Pizza Type: " + type);
        }
    }
}