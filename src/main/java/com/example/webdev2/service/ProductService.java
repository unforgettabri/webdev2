package com.example.webdev2.service;

import com.example.webdev2.model.Product;
import com.example.webdev2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsAbovePrice(double threshold) {
        return productRepository.getProducts()
                .stream()
                .filter(product -> product.getPrice() > threshold)
                .collect(Collectors.toList());
    }
}