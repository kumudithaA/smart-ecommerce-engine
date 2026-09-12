package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.strategy.DiscountStrategy;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("C01","Alice", "alice@example.com");
        Order order = new Order("ORD-100",customer);

        order.addProduct(new PhysicalProduct("P01","Laptop", 1000.0, Category.ELECTRONICS, 2.5));
        order.addProduct(new DigitalProduct("P02", "E-Book", 20.0, Category.BOOKS, "http://download.link"));

        DiscountStrategy tenPercentOff = total -> total * 0.90;
        DiscountStrategy flat50Off = total -> total - 50.0;

        DiscountStrategy combinedDiscount = tenPercentOff.combine(flat50Off);

        System.out.println("Raw Total with Tax: $"+ order.calculateRawTotal());
        System.out.println("Final price after Combined Discount: $" + order.calculateFinalTotal(combinedDiscount));
    }
}