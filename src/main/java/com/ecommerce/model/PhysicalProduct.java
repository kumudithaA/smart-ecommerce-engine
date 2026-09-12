package com.ecommerce.model;

public class PhysicalProduct extends Product{

    private double weightKg;

    public PhysicalProduct(String id, String name, double price, Category category, double weightKg) {
        super(id, name, price, category);
        setWeightKg(weightKg);
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        if(weightKg <= 0 ){
            throw new IllegalArgumentException("Weight must be grater than Zero"+ weightKg);
        }
        this.weightKg = weightKg;
    }

    @Override
    public double calculateTax() {
        double baseTax = getPrice() * 0.1;
        double shippingSercharge = weightKg * 2.0;
        return baseTax + shippingSercharge;
    }

    @Override
    public String toString() {
        return "PhysicalProduct{" +
                "id='"+ getId() + '\'' +
                ",name='"+ getName() + '\'' +
                ",price="+ getPrice() +
                ",weightKg=" + weightKg +
                '}';
    }
}
