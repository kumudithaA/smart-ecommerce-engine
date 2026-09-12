package com.ecommerce.strategy;

@FunctionalInterface
public interface DiscountStrategy {

    double apply(double totalAmount);

    default DiscountStrategy combine(DiscountStrategy nextStrategy){
        return total ->   nextStrategy.apply(this.apply(total));
    }
}
