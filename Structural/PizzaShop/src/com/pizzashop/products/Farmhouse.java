package com.pizzashop.products;

import com.pizzashop.base.Pizza;

public class Farmhouse extends Pizza {
    public Farmhouse() {
        description = "Farmhouse Pizza";
    }

    @Override
    public double getCost() {
        return 300.00;
    }
}