package com.example.product.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {
    }


    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.put(product, products.getOrDefault(product, 0) + 1);
    }

    public void updateProductQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            products.remove(product);
        } else {
            products.put(product, quantity);
        }
    }

    public Integer countProductQuantity() {
        return products.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Float countTotalPayment() {
        return (float) products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice() * entry.getValue())
                .reduce(0.0, Double::sum)
                .floatValue();
    }
}

