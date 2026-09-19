package com.ecommerce.model;

public abstract class Product {

    private String id;
    private String name;
    private double price;
    private Category category;

    public Product(){
        System.out.println("A new product has been added");
    }
    public Product(String id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        setPrice(price);
        this.category = category;
    }

    public abstract double calculateTax();


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }



    public void setPrice(double price) {
        if (price <= 0){
            throw new IllegalArgumentException("Price must be better than zero " + price);
        }
        this.price = price;
    }



}
