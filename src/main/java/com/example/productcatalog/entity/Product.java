package com.example.productcatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 150)
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "SKU is required")
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String sku;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock must be zero or greater")
    @Column(nullable = false)
    private Integer stockQuantity;

    @NotBlank(message = "Status is required")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category is required")
    private Category category;

    public Product() {}

    public Product(String name, String sku, Double price,
                   Integer stockQuantity, String status, Category category) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public Double getPrice() { return price; }
    public Integer getStockQuantity() { return stockQuantity; }
    public String getStatus() { return status; }
    public Category getCategory() { return category; }

    public void setName(String name) { this.name = name; }
    public void setSku(String sku) { this.sku = sku; }
    public void setPrice(Double price) { this.price = price; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setStatus(String status) { this.status = status; }
    public void setCategory(Category category) { this.category = category; }
}
