package com.example.webdev2.service;

import com.example.webdev2.config.ShopProperties;
import com.example.webdev2.model.Product;
import com.example.webdev2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopProperties shopProperties;

    public ProductService(ProductRepository productRepository, ShopProperties shopProperties) {
        this.productRepository = productRepository;
        this.shopProperties = shopProperties;
    }

    public List<Product> getProductsAbovePrice(double threshold) {
        return productRepository.getProducts()
                .stream()
                .filter(product -> product.getPrice() > threshold)
                .collect(Collectors.toList());
    }

    public String getShopName() {
        return shopProperties.getName();
    }

    public String getCurrency() {
        return shopProperties.getCurrency();
    }
}