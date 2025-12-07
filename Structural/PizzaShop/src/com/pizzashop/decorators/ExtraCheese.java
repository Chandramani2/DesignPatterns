package com.pizzashop.decorators;

import com.pizzashop.base.Pizza;

public class ExtraCheese extends ToppingDecorator {
    Pizza pizza;

    public ExtraCheese(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Extra Cheese";
    }

    @Override
    public double getCost() {
        return 50.00 + pizza.getCost();
    }
}