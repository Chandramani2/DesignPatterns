package com.pizzashop.decorators;

import com.pizzashop.base.Pizza;

public abstract class ToppingDecorator extends Pizza {
    public abstract String getDescription();
}