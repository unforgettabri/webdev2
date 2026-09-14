package com.example.webdev2;

import com.example.webdev2.model.Product;
import com.example.webdev2.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Webdev2Application {

    public static void main(String[] args) {
        SpringApplication.run(Webdev2Application.class, args);
    }

    @Bean
    CommandLineRunner startupReport(ProductService productService) {
        return args -> {
            System.out.println("=== SHOP REPORT ===");
            System.out.println("Shop: " + productService.getShopName());
            System.out.println("Currency: " + productService.getCurrency());
            System.out.println("Products above PHP 5000:");

            for (Product product : productService.getProductsAbovePrice(5000)) {
                System.out.println(product.getName() + " - " 
                        + productService.getCurrency() + " " + product.getPrice());
            }
        };
    }
}