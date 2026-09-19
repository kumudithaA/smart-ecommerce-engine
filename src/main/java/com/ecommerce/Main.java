package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.strategy.DiscountStrategy;

import java.lang.reflect.Array;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("C01", "Alice", "alice@example.com");
        Order order = new Order("ORD-100", customer);

        order.addProduct(new PhysicalProduct("P01", "Laptop", 1000.0, Category.ELECTRONICS, 2.5));
        order.addProduct(new DigitalProduct("P02", "E-Book", 20.0, Category.BOOKS, "http://download.link"));

        DiscountStrategy tenPercentOff = total -> total * 0.90;
        DiscountStrategy flat50Off = total -> total - 50.0;

        DiscountStrategy combinedDiscount = tenPercentOff.combine(flat50Off);

        System.out.println("Raw Total with Tax: $" + order.calculateRawTotal());
        System.out.println("Final price after Combined Discount: $" + order.calculateFinalTotal(combinedDiscount));


        List<Customer> customers = Arrays.asList(
                new Customer("C1", "Alice", "alice@gmail.com"),
                new Customer("C2", "Rayan", "rayan@yahoo.com"),
                new Customer("C3", "Bob", "bob3@gmail.com")
        );

        List<Product> products = Arrays.asList(
                new PhysicalProduct("P01", "Laptop", 1000.0, Category.ELECTRONICS, 2.5),
                new PhysicalProduct("P02", "Desk Lamp", 45.0, Category.ELECTRONICS, 1.2),
                new DigitalProduct("P03", "Java 8 E-Book", 29.9, Category.BOOKS, "http://link1.com"),
                new DigitalProduct("P04", "Design Patterns PDF", 15.0, Category.BOOKS, "http://pdflink2.link"),
                new PhysicalProduct("P05", "Standing Desk", 450.0, Category.FURNITURE, 25.0)
        );


        Order o1 = new Order("O101", customers.get(0));
        o1.addProduct(products.get(0));
        o1.addProduct(products.get(2));

        Order o2 = new Order("O102", customers.get(1));
        o2.addProduct(products.get(1));

        Order o3 = new Order("O103", customers.get(0));
        o3.addProduct(products.get(4));

        List<Order> orders = Arrays.asList(o1, o2, o3);

        listExpensivePhysicalProductName(products);

        Optional<Product> p1 = findProductByName(products, "Java 8 E-Book");
        Optional<Product> p2 = findProductByName(products, "Monitor");

        List<Optional<Product>> searchProducts = Arrays.asList(p1, p2);
        searchProducts.forEach(p -> p.ifPresent(product -> System.out.println(product.getName() + " " + product.getPrice())));

        findProductByCategory(products);

        totalAmount(products);

    }

    public static void listExpensivePhysicalProductName(List<Product> products) {

        List<String> expensivePhysicalProductName = products.stream()
                .filter(p -> p instanceof PhysicalProduct)
                .filter(p -> p.getPrice() > 20.0)
                .map(Product::getName)
                .collect(Collectors.toList());

        expensivePhysicalProductName.forEach(System.out::println);

    }

    public static Optional<Product> findProductByName(List<Product> products, String searchName) {

        Optional<Product> product = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(searchName)).findFirst();
        return product;
    }

    public static void findProductByCategory(List<Product> products) {

        java.util.Map<Category, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        productsByCategory.forEach(((category, productList) -> {
            System.out.println("Category: " + category);
            productList.forEach(p -> System.out.println(" -> " + p.getName() + "($" + p.getPrice() + ")"));
        }));

    }

    public static void totalAmount(List<Product> products) {

        double grandTotal = products.stream()
                .mapToDouble(p -> p.getPrice())
                .sum();

        System.out.println("Grand Total: $" + grandTotal);

        Optional<Product> mostExpensive = products.stream()
                .max(Comparator.comparingDouble(Product::getPrice));

        mostExpensive.ifPresent(p -> System.out.println("Most Expensive Product: " + p.getName()));


        products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .forEach(p -> System.out.println(p.getName() + " - $" + p.getPrice()));
    }

}