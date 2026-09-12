package com.ecommerce.model;

import com.ecommerce.strategy.DiscountStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<Product> items;
    private final LocalDateTime orderDate;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public void addProduct(Product product){
        this.items.add(product);
    }

    public double calculateRawTotal(){
        double total = 0.0;
        for(Product item:items){
            total += item.getPrice() + item.calculateTax();
        }
        return total;
    }

    public double calculateFinalTotal(DiscountStrategy discountStrategy){
        double rawTotal = calculateRawTotal();
        return discountStrategy.apply(rawTotal);

    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
