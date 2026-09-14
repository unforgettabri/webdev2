package com.example.webdev2.repository;

import com.example.webdev2.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> products = List.of(
            new Product("Laptop", 45000),
            new Product("Monitor", 12000),
            new Product("Keyboard", 2500),
            new Product("Printer", 8500)
    );

    public List<Product> getProducts() {
        return products;
    }
}