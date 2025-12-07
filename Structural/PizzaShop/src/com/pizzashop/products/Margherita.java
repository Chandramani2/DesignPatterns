package com.pizzashop.products;

import com.pizzashop.base.Pizza;

public class Margherita extends Pizza {
    public Margherita() {
        description = "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 200.00;
    }
}