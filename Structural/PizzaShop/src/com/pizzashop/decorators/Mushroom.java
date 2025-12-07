package com.pizzashop.decorators;

import com.pizzashop.base.Pizza;

public class Mushroom extends ToppingDecorator {
    Pizza pizza;

    public Mushroom(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    @Override
    public double getCost() {
        return 40.00 + pizza.getCost();
    }
}